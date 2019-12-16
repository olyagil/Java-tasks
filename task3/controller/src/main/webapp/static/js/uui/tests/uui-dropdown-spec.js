describe("uui-dropdown", function () {
    'use strict';
    var $dropdown, $status_dropdown;

    beforeEach(function () {
        try {
            jasmine.getFixtures().fixturesPath = 'base/js/uui/tests/fixtures';
            loadFixtures('dropdown.html');
        }
        catch (ex) {
            jasmine.getFixtures().fixturesPath = 'js/uui/tests/fixtures';
            loadFixtures('dropdown.html');
        }
        $dropdown = $('#default_dropdown');
        $dropdown.uui_dropdown();

        $status_dropdown = $('#status_dropdown');
        $status_dropdown.find('option').first().attr('selected', 'selected');
        $status_dropdown.uui_dropdown();
    });

    afterEach(function () {
        $dropdown.parent().remove();
        $status_dropdown.parent().remove();
    });

    it("should be initialized properly", function () {
        expect($dropdown.parent()).toHaveClass('bootstrap-select');
        expect($dropdown.parent().find('li').length).toEqual($dropdown.find('option').length);
    });

    it("should set value and get value (base bootstrap selectpicker methods)", function () {
        var selected_value = $dropdown.find('option')[1].value;
        $dropdown.uui_dropdown('val', selected_value);
        expect($dropdown.parent().find('li')[1]).toHaveClass('selected');
        expect($dropdown.val()).toEqual(selected_value);
    });

    it("status dropdown should be initialized properly", function () {
        expect($status_dropdown.parent()).toHaveClass('bootstrap-select');
        expect($status_dropdown.parent()).toHaveClass($status_dropdown.find('option:selected')[0].className);
        expect($status_dropdown.parent().find('li').length).toEqual($dropdown.find('option').length);
    });

    it("should change color on value change", function () {
        var selected_option = $status_dropdown.find('option')[1];
        var selected_value = selected_option.value;
        $status_dropdown.uui_dropdown('val', selected_value);
        expect($status_dropdown.parent().find('li')[0]).toHaveClass('selected');
        expect($status_dropdown.val()).toEqual(selected_value);
        expect($status_dropdown.parent()).toHaveClass(selected_option.className);
    });
});
