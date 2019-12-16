describe("uui-tabs", function () {
    'use strict';

    beforeEach(function () {
        try {
            jasmine.getFixtures().fixturesPath = 'base/js/uui/tests/fixtures';
            loadFixtures('tab.html');
        }
        catch (ex) {
            jasmine.getFixtures().fixturesPath = 'js/uui/tests/fixtures';
            loadFixtures('tab.html');
        }
    });

    it("should be initialized properly", function () {
        var $tabs = $('.uui-tabs');
        $($tabs.find('.tabs-heading a')[2]).trigger('click');
        expect($tabs.find('.tab-pane')[2]).toHaveClass('active');
        $($tabs.find('.tabs-heading a')[0]).trigger('click');
        $($tabs.find('.sub-tabs-heading a')[3]).trigger('click');
        expect($tabs.find('.sub-tab-pane')[3]).toHaveClass('active');
    });
});
