function getLoader() {
    return '<div class="progress"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%"></div></div>';
};
function $getContent(url, data, destination) {
    $(destination).css('position', 'relative');
    $('<div />').addClass('loading-splash').html(getLoader()).appendTo(destination);
    
    $.get(url, data, function (ret) {
        $(destination).html(ret);
    }).fail(function () {
        $(destination).html('');
        var $alert = $('<div />').addClass('alert alert-danger').appendTo(destination);
        $('<a />')
            .html('RETRY')
            .attr('href', 'javascript:void(0)')
            .on('click', function() {
                $getContent(url, data, destination)
            })
            .appendTo($alert);
    });
};
function refreshBlock(destination, url, data, scroll) {
    if (scroll) {
        var aTag = $('#return-top');
        if (aTag.length > 0) {
            $('html,body').animate({
                scrollTop: aTag.offset().top + $(document).scrollTop()
            }, 'slow');
        }
    }
    
    $getContent(url, data, destination);
};
function openWindow(url, data, extra) {
    var options = $.extend({
        'title': '&nbsp;',
        'message': '&nbsp;'
    }, extra);
    var dialog = bootbox.dialog(options);
    
    $getContent(url, data, $(dialog).find('.bootbox-body'));
    
    return dialog;
};
function alertJs(msg) {
    bootbox.alert({
        'title': '&nbsp;',
        'message': msg
    });
};

$(function () {
    // Tooltips
    $('body').tooltip({
        selector: '[data-toggle="tooltip"]'
    });
    
    // Slider de cambio de página
    $(window).on('beforeunload', function () {
        if ($('.loading-info').length === 0) {
            var $container = $('<div />')
                .addClass('loading-info slider')
                .appendTo('body');
            $('<div class="line"></div>').appendTo($container);
            $('<div class="break dot1"></div>').appendTo($container);
            $('<div class="break dot2"></div>').appendTo($container);
            $('<div class="break dot3"></div>').appendTo($container);
        }
    });
});