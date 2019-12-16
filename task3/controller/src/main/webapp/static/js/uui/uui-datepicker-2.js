(function ($) {
    'use strict';
    $.fn.uui_datepicker_2 = function (options, value) {
        var self = this;
        var result_object;
        var options_is_object = (typeof options === 'object');
        if (options_is_object && 'timepicker' in options && 'timepicker') {
            result_object = self.datetimepicker(options, value);
        }
        else {
            result_object = self.datepicker(options, value);
        }
        if (options_is_object && 'color' in options) {
            self.find('.ui-datepicker-inline').addClass(options.color);
            if (result_object instanceof jQuery) {
                result_object.on('show', function() {
                    $('body').find('.ui-datepicker').addClass(options.color);
                });
            }
        }
        return result_object;
    };
}(jQuery));