function toggleWikiNode(icon) {
    var
        $i = $(icon),
        $div = $i.parent(),
        expand = $div.attr('expand');
    if (expand === 'true') {
        collapseWikiNode(icon);
    } else {
        expandWikiNode(icon);
    }
}

function collapseWikiNode(icon, rec) {
    var
        $i = $(icon),
        $div = $i.parent();
    $div.attr('expand', 'false');
    $i.removeClass('uk-icon-minus-square-o');
    $i.addClass('uk-icon-plus-square-o');
    $div.find('>div').hide();
    if (rec) {
        $div.find('>div>i').each(function () {
            collapseWikiNode(this, rec);
        });
    }
}

function expandWikiNode(icon, rec) {
    var
        $i = $(icon),
        $div = $i.parent();
    $div.attr('expand', 'true');
    $i.removeClass('uk-icon-plus-square-o');
    $i.addClass('uk-icon-minus-square-o');
    $div.find('>div').show();
    if (rec) {
        $div.find('>div>i').each(function () {
            expandWikiNode(this, rec);
        });
    }
}

function expandActiveNode() {
    var activeNode = $('#0013739516305929606dd18361248578c67b8067c8c017b000');
    for (; ;) {
        i = activeNode.find('i').get(0);
        if (i) {
            expandWikiNode(i);
        }
        activeNode = activeNode.parent();
        if (!activeNode.is('div')) {
            break;
        }
    }
}

function appentLeft(data) {
    var str = "";
    for (var i = 0; i < data.length; i++) {
        var tree = data[i];
        if (tree.isParent) {
            str += "<div id=\"" + tree.id + "\" depth=\"0\" style=\"position:relative;margin-left:15px;\" class=\"uk-active\" expand=\"true\">"
                + "<i onclick=\"toggleWikiNode(this)\" class=\"uk-icon-minus-square-o\" style=\"position:absolute;margin-left:-1em;padding-top:8px;\"></i>"
                + "<a onclick=\"loadConent('"+tree.id+"','" + tree.url + "',this)\" class=\"x-wiki-index-item\">" + tree.name + "</a>"
                +  appentChildrenLeft(tree.children,str)
                + "</div>";
        } else {
            str += "<div id=\"" + tree.id + "\" depth=\"1\" style=\"position: relative; margin-left: 15px;\">"
                + "<a onclick=\"loadConent('"+tree.id+"','" + tree.url + "',this)\" class=\"x-wiki-index-item\">" + tree.name + "</a>"
                + "</div>";
        }
    }
    return str;
}

function appentChildrenLeft(data,str) {
    for (var i = 0; i < data.length; i++) {
        var tree = data[i];
        tree.ch
        if (tree.isParent) {
            str += "<div id=\"" + tree.id + "\" depth=\"1\" style=\"position: relative; margin-left: 15px;\">"
                +"<i onclick=\"toggleWikiNode(this)\" class=\"uk-icon-plus-square-o\" style=\"position:absolute;margin-left:-1em;padding-top:8px;\"></i>"
                +"<a onclick=\"loadConent('"+tree.id+"','" + tree.url + "',this)\" class=\"x-wiki-index-item\">" + tree.name + "</a>"
                +  appentChildrenLeft(tree.children,str)
                + "</div>";
        } else {
            str += "<div id=\"" + tree.id + "\" depth=\"1\" style=\"position: relative; margin-left: 15px;\">"
                + "<a onclick=\"loadConent('"+tree.id+"','" + tree.url + "',this)\" class=\"x-wiki-index-item\">" + tree.name + "</a>"
                + "</div>";
        }
    }
    return str;
}

function loadConent(id,url,thi) {
    var menuselect = $("#x-wiki-index").find(".uk-active").removeClass("uk-active");
    $("#"+id).addClass('uk-active');
    $("#blog_conent").load("/blog/record_info?id="+id);
}