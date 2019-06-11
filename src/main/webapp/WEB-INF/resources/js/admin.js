function $activate(el, url, data, value, clsTrue, clsFalse) {
    var $container = $(el).parent();
    $(el).hide();
    var $spinner = $('<i class="fa fa-spinner fa-spin animatedx"></i>').appendTo($container);
    $.post(url, $.extend({ 'value': !value }, data), function (ret) {
        if (ret.result === true) {
            if (value) {
                $(el)
                    .data("value", !value)
                    .removeClass(clsTrue)
                    .addClass(clsFalse);
            } else {
                $(el)
                    .data("value", !value)
                    .removeClass(clsFalse)
                    .addClass(clsTrue);
            }
        }
    }).always(function () {
        $spinner.remove();
        $(el).show();
    });
};
function $action(el, url, data) {
    var $container = $(el).parent();
    $(el).hide();
    var $spinner = $('<i class="fa fa-spinner fa-spin animatedx"></i>').appendTo($container);
    $.post(url, $.extend({ 'value': !value }, data), function (ret) {
        if (ret.result === true) {
            if (value) {
                $(el)
                    .data("value", !value)
                    .removeClass(clsTrue)
                    .addClass(clsFalse);
            } else {
                $(el)
                    .data("value", !value)
                    .removeClass(clsFalse)
                    .addClass(clsTrue);
            }
        }
    }).always(function () {
        $spinner.remove();
        $(el).show();
    });
};
function $browserSelect(field, type, value, url) {
    if (type == 'bimage') {
        $('*[name=\'' + field + '\']').val(value);
        $('#' + field).val(value);
    } else {
        $('*[name=\'' + field + '\']').val(url);
        $('#' + field).val(url);
    }
    $('.' + field + '-img').attr('src', url);

    $('.browser-modal').modal('hide');
};
function $browserChange(url, path, field, type) {
    var data = {
        "path": path,
        "field": field,
        "type": type
    };
    $getContent(url, data, $('.browser').parent());
};