(function ($) {
    'use strict';
    $.fn.uui_dropdown = function (options, value) {
        var self = this;
        var result_object = self.selectpicker(options, value);

        $.each(self, function (i, element) {
            var $element = $(element);
            if ($element.hasClass('status-dropdown')) {
                var optionColor = $element.find(':selected').attr('class');
                if (optionColor !== 'bs-title-option') {
                    $element.parents('.status-dropdown').addClass(optionColor);
                }
                self.change(function () {
                    var optionColor = $(this).find(':selected').attr('class');
                    $(this).parents('.status-dropdown').removeClass('status-blue status-lime-green status-dark-gray ' +
                        'status-raspberry status-plum status-dark-blue status-dark-green status-yellow status-orange ' +
                        'status-red status-fuchsia').addClass(optionColor);
                });
            }
        });
        return result_object;
    };
}(jQuery));