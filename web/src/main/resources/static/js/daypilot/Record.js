const nav = new DayPilot.Navigator("nav");
nav.locale = "ru-ru";
nav.showMonths = 3;
nav.skipMonths = 3;
nav.selectMode = "week";

nav.onTimeRangeSelect = (args) => {
    if (args.day < DayPilot.Date.today()) {
        args.preventDefault();
        nav.select(LastDate, {dontNotify: true, dontFocus: true});
    } else {
        LastDate = args.start;
    }
};

nav.onBeforeCellRender = function (args) {
    if (args.cell.day < DayPilot.Date.today()) {
        args.cell.cssClass = "navigator-disabled-cell";
    }
};

nav.onTimeRangeSelected = (args) => {
    dp.startDate = args.day;
    dp.update();
    dp.events.load("/record/events");
};
nav.init();

const dp = new DayPilot.Calendar("dp");
dp.locale = "ru-ru";
dp.heightSpec = "Fixed";
dp.height = 691;
dp.allowEventOverlap = false;
dp.columnMarginRight = 0;
dp.viewType = "Week";

dp.onBeforeCellRender = function (args) {
    const now = new DayPilot.Date().getTime();
    if (args.cell.start.getTime() <= now) {
        args.cell.disabled = true;
        args.cell.BackColor = "#eee";
    }
};

dp.onEventFilter = function(args) {
    if (args.e.text().toUpperCase().indexOf(args.filter.toUpperCase()) === -1) {
        args.visible = false;
    }
};

dp.onTimeRangeSelected = function (args) {
        const form = [
            {name: "Новое событие"},
            {name: "Тема", id: "text" },
            {name: "Начало", id: "start", type: "datetime"},
            {name: "Конец", id: "end", type: "datetime"},
        ];
        const par = {
            text: "Тема",
            start: args.start.toString(),
            end: args.end.toString()
        }
        DayPilot.Modal.form(form, par).then(function (modal){
        const dp = args.control;
        dp.clearSelection();
        if (!modal.result) {
            return;
        }
        const params = {
            start: modal.result.start,
            end: modal.result.end,
            text: modal.result.text
        };
        DayPilot.Http.ajax({
            url: '/record/events/create',
            data: params,
            success: function (ajax) {
                const data = ajax.data;
                dp.events.add(new DayPilot.Event(data));
                dp.message("Событие создано");
            }
        });
    });
};
dp.onEventClicked = function (args) {
    const form = [
        {name: "Изменение события"},
        {name: "Тема", id: "text"},
        {name: "Начало", id: "start", type: "datetime"},
        {name: "Конец", id: "end", type: "datetime"},
        {
            type: 'select',
            id: 'color',
            name: 'Цвет',
            options: [
                {
                    color: "#1066a8",
                    name: 'Синий',
                    id: 'blue',
                },
                {
                    color: "#6aa84f",
                    name: 'Зелёный',
                    id: 'green',
                },
                {
                    color: "#f1c232",
                    name: 'Жёлтый',
                    id: 'yellow',
                },
                {
                    color: "#cc0000",
                    name: 'Красный',
                    id: 'red',
                },
            ],
        },
    ];
    DayPilot.Modal.form(form, args.e.data).then(function (modal){
        const dp = args.control;
        if (!modal.result) {
            return;
        }
        const params = {
            id: args.e.id(),
            start: modal.result.start,
            end: modal.result.end,
            text: modal.result.text,
            color: modal.result.color
        }
        DayPilot.Http.ajax({
            url: '/record/events/modal',
            data: params,
            success: function (ajax) {
                dp.events.update(modal.result);
                dp.message("Событие изменено");
            }
        });
    });
};

dp.bubble = new DayPilot.Bubble();
dp.events.list = [
    {
        start: "2013-03-25T00:00:00",
        end: "2013-03-25T12:00:00",
        id: "123",
        text: "Event"
    }
];

dp.eventDoubleClickHandling = "Edit";
dp.onEventEdited = function (args) {
    const params = {
        id: args.e.id(),
        text: args.newText.toString()
    };
    DayPilot.Http.ajax({
        url: '/record/events/edit',
        data: params,
        success: function (ajax) {
            dp.message("Тема события изменена на " + args.newText);
        }
    });
};

dp.onEventMove = function (args) {
    const params = {
        id: args.e.id(),
        start: args.newStart.toString(),
        end: args.newEnd.toString()
    };
    DayPilot.Http.ajax({
        url: '/record/events/move',
        data: params,
        success: function (ajax) {
            dp.message("Событие перенесено");
        }
    });
};
dp.onEventResize = function (args) {
    const params = {
        id: args.e.id(),
        start: args.newStart.toString(),
        end: args.newEnd.toString()
    };
    DayPilot.Http.ajax({
        url: '/record/events/move',
        data: params,
        success: function (ajax) {
            dp.message("Время события изменено");
        }
    });
};
dp.onBeforeEventRender = function (args) {
    args.data.barColor = args.data.color;
    args.e.bubbleHtml = args.e.start + " - " + args.e.end + " " + args.e.text;
    args.data.areas = [
        {
            top: 2,
            right: 2,
            width: 18,
            height: 18,
            icon: "icon-triangle-down",
            visibility: "Visible",
            action: "ContextMenu",
            style: "font-size: 12px; background-color: #f9f9f9; border: 1px solid #ccc; padding: 2px 2px 0px 2px; cursor:pointer; box-sizing: border-box; border-radius: 15px;"
        }
    ];
};
dp.contextMenu = new DayPilot.Menu({
    items: [
        {
            text: "Синий",
            icon: "icon icon-blue",
            color: "#1066a8",
            onClick: function (args) {
                updateColor(args.source, args.item.color);
            }
        },
        {
            text: "Зелёный",
            icon: "icon icon-green",
            color: "#6aa84f",
            onClick: function (args) {
                updateColor(args.source, args.item.color);
            }
        },
        {
            text: "Жёлтый",
            icon: "icon icon-yellow",
            color: "#f1c232",
            onClick: function (args) {
                updateColor(args.source, args.item.color);
            }
        },
        {
            text: "Красный",
            icon: "icon icon-red",
            color: "#cc0000",
            onClick: function (args) {
                updateColor(args.source, args.item.color);
            }
        },

        {
            text: "Удалить",
            onClick: function (args) {
                deleteEvents(args.source)
            }
        },

    ]
});
dp.init();
dp.events.load("/record/events");

$(document).ready(function() {
    $("#filter").keyup(function () {
        const query = $(this).val();
        dp.events.filter(query);
    });

    $("#clear").click(function () {
        $("#filter").val("");
        dp.events.filter(null);
        return false;
    });
});

function updateColor(e, color) {
    const params = {
        id: e.id(),
        color: color
    };
    DayPilot.Http.ajax({
        url: '/record/events/setColor',
        data: params,
        success: function (ajax) {
            e.data.color = color;
            dp.events.update(e);
            dp.message("Цвет изменён");
        }
    });
}

function deleteEvents(e) {
    const params = {
        id: e.id()
    };
    DayPilot.Http.ajax({
        url: '/record/events/delete', params,
        data: params,
        success: function (ajax) {
            dp.events.remove(e);
            dp.message("Событие удалено");
        }
    });
}