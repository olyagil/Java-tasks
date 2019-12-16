/*jslint node: true */

module.exports = function (grunt) {
    'use strict';
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        jshint: {
            options: {
                jshintrc: true,
                reporter: require('jshint-stylish')
            },
            files: ['js/uui/**/*.js']
        },
        jasmine: {
            pivotal: {
                src: [
                    'bootstrap/js/bootstrap.min.js',
                    'js/uui/*.js',
                    'js/uui/tests/*.js'
                ],
                options: {
                    vendor: [
                        'node_modules/jquery/dist/jquery.js',
                        'node_modules/jasmine-jquery/lib/jasmine-jquery.js',
                        'js/lib/components/jquery.mCustomScrollbar.concat.min.js',
                        'js/lib/components/bootstrap-datepicker.js',
                        'js/lib/components/bootstrap-timepicker.js',
                        'js/lib/components/bootstrap-select.min.js'
                    ],
                    specs: 'tests/*-spec.js'
                }
            }
        },
        copy: {
            main: {
                files: [
                    {expand: true, src: ['fonts/**'], dest: 'uui'},
                    {expand: true, src: ['bootstrap/**'], dest: 'uui'},
                    {expand: true, src: ['jquery-ui/**'], dest: 'uui'},
                    {expand: true, src: ['images/**'], dest: 'uui'},
                    {expand: true, src: ['tinymce/**'], dest: 'uui'},
                    {expand: true, src: ['slick/**'], dest: 'uui'},
                    {expand: true, src: ['less/*'], dest: 'uui/less', filter: 'isFile', flatten: true},
                    {expand: true, cwd: 'js', src: ['lib/**'], dest: 'uui/js'},
                    {expand: true, cwd: 'js/uui', src: ['!tests/**/*'], dest: 'uui/js'},
                    {expand: true, cwd: 'css', src: ['lib/**'], dest: 'uui/css'}
                ]
            }
        },
        uglify: {
            dist: {
                files: {
                    'uui/js/uui-core.min.js': 'js/uui/uui-core.js',
                    'uui/js/uui-datepicker.min.js': 'js/uui/uui-datepicker.js',
                    'uui/js/uui-datepicker-2.min.js': 'js/uui/uui-datepicker-2.js',
                    'uui/js/uui-global-search.min.js': 'js/uui/uui-global-search.js',
                    'uui/js/uui-multiswitch.min.js': 'js/uui/uui-multiswitch.js',
                    'uui/js/uui-panel.min.js': 'js/uui/uui-panel.js',
                    'uui/js/uui-popover.min.js': 'js/uui/uui-popover.js',
                    'uui/js/uui-rating.min.js': 'js/uui/uui-rating.js',
                    'uui/js/uui-show-text.min.js': 'js/uui/uui-show-text.js',
                    'uui/js/uui-tagit.min.js': 'js/uui/uui-tagit.js',
                    'uui/js/uui-timepicker.min.js': 'js/uui/uui-timepicker.js',
                    'uui/js/uui-tooltip.min.js': 'js/uui/uui-tooltip.js',
                    'uui/js/uui-tree-grid.min.js': 'js/uui/uui-tree-grid.js',
                    'uui/js/uui-autocomplete.min.js': 'js/uui/uui-autocomplete.js',
                    'uui/js/uui-dropdown.min.js': 'js/uui/uui-dropdown.js',
                    'uui/js/uui-tabs.min.js': 'js/uui/uui-tabs.js'
                }
            }
        },
        concat: {
            options: {
                process: function (content) {
                    return content.replace(/@import 'uui-fonts.less';/g, '')
                        .replace(/@import 'uui-icons.less';/g, '')
                        .replace(/@import 'uui-colors.less';/g, '')
                        .replace(/^(?:[\t ]*(?:\r?\n|\r))+/g, '');
                }
            },
            dist: {
                src: ['less/uui-fonts.less', 'less/uui-colors.less', 'less/uui-icons.less', 'less/uui-core.less',
                    'less/!(uui-fonts|uui-colors|uui-core|uui-icons).less'],
                dest: 'uui/less/uui-all.less'
            }
        },
        less: {
            compile: {
                options: {
                    paths: ['less'],
                    cleancss: false
                },
                files: [{
                    expand: true,
                    cwd: 'less',
                    src: ['*.less', '!{uui-fonts,uui-colors,uui-icons}.less'],
                    ext: '.css',
                    dest: 'uui/css'
                }]
            },
            compile_min: {
                options: {
                    paths: ['less'],
                    cleancss: true
                },
                files: [{
                    expand: true,
                    cwd: 'less',
                    src: ['*.less', '!{uui-fonts,uui-colors,uui-icons}.less'],
                    ext: '.min.css',
                    dest: 'uui/css'
                }]
            },
            compile_concatenated: {
                options: {
                    paths: ['css'],
                    cleancss: false,
                    strictImports: true
                },
                files: {
                    'uui/css/uui-all.css': 'uui/less/uui-all.less'
                }
            },
            compile_concatenated_min: {
                options: {
                    paths: ['css'],
                    cleancss: true,
                    strictImports: true
                },
                files: {
                    'uui/css/uui-all.min.css': 'uui/less/uui-all.less'
                }
            },
            compile_on_watch: {
                options: {
                    paths: ['less'],
                    cleancss: false
                },
                files: [{
                    expand: true,
                    cwd: 'less',
                    src: ['*.less', '!{uui-fonts,uui-colors,uui-icons}.less'],
                    ext: '.css',
                    dest: 'css'
                }]
            }
        },
        compress: {
            main: {
                options: {
                    archive: 'uui.zip'
                },
                files: [
                    {src: ['uui/**'], dest: './'}
                ]
            }
        },
        clean: ['uui'],
        watch: {
            css: {
                files: ['less/*.less'],
                tasks: ['newer:less:compile_on_watch'],
                options: {
                    spawn: false
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-contrib-jasmine');
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-less');
    grunt.loadNpmTasks('grunt-contrib-uglify');
    grunt.loadNpmTasks('grunt-contrib-copy');
    grunt.loadNpmTasks('grunt-contrib-compress');
    grunt.loadNpmTasks('grunt-contrib-clean');
    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-newer');

    grunt.registerTask('test', ['jshint', 'jasmine']);
    grunt.registerTask('uui', ['jshint', 'jasmine', 'copy', 'uglify', 'concat', 'less:compile', 'less:compile_min',
        'less:compile_concatenated', 'less:compile_concatenated_min']);
    grunt.registerTask('uui_zip_package', ['jshint', 'jasmine', 'copy', 'uglify', 'concat', 'less:compile',
        'less:compile_min', 'less:compile_concatenated', 'less:compile_concatenated_min', 'compress', 'clean']);
};