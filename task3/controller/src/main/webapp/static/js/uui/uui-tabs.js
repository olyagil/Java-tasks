$(document).ready(function () {
    'use strict';
    var $sub_tabs = $('.sub-tabs-heading').find('li');

    $sub_tabs.click(function () {
        $(this).parents('.uui-sub-tabs').find('.sub-tab-pane').removeClass('active');
        $(this).parents('.uui-sub-tabs').find('.sub-tab-pane').each(function () {
            $(this).addClass('active');
        });
    });
});