$(function () {
        {
            $.ajax({
                type: "GET",
                url: "/friends"
            }).done(function (friends) {

                $("#jsGrid").jsGrid({
                    height: "auto",
                    width: "80%",

                    filtering: false,
                    inserting: true,
                    editing: true,
                    sorting: false,
                    paging: true,
                    autoload: true,

                    pageSize: 10,
                    pageButtonCount: 5,

                    deleteConfirm: "Do you really want to delete item?",

                    controller: {
                        loadData: function (filter) {
                            console.log("Get items called...." + JSON.stringify(filter));

                            var d = $.Deferred();
                            $.ajax({
                                type: "GET",
                                url: "/items",
                                data: filter
                            }).done(function (response) {
                                var x = response.map(transformItem);
                                console.log(JSON.stringify(x));
                                d.resolve(x);
                            });
                            return d.promise();
                        },

                        insertItem: function (item) {
                            console.log("Insert called...." + JSON.stringify(item));

                            var d = $.Deferred();
                            $.ajax({
                                type: "POST",
                                url: "/item",
                                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                                data: {name: item.name, friendId: item.friend}
                            }).done(function (item) {
                                var x = transformItem(item);
                                console.log(JSON.stringify(x));
                                d.resolve(x);
                            });
                            return d.promise();
                        },

                        updateItem: function (item) {
                            console.log("Update called...." + JSON.stringify(item));

                            var d = $.Deferred();
                            $.ajax({
                                type: "PUT",
                                url: "/item/" + item.id,
                                contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                                data: {name: item.name, friendId: item.friend}
                            }).done(function (item) {
                                var x = transformItem(item);
                                console.log(JSON.stringify(x));
                                d.resolve(x);
                            });
                            return d.promise();
                        },

                        deleteItem: function (item) {
                            console.log("Delete called...." + JSON.stringify(item));

                            return $.ajax({
                                type: "DELETE",
                                url: "/item/" + item.id
                            });
                        }
                    },

                    fields: [
                        {type: "text", width: 50, name: "id", editing: false},
                        {title: "Item name", type: "text", width: 150, name: "name", editing: true},
                        {
                            title: "Friend",
                            name: "friend",
                            type: "select",
                            items: friends,
                            valueField: "id",
                            textField: "name"
                        },
                        {type: "control"}
                    ]
                });
            });

            function transformItem(item) {
                return {
                    id: item.id,
                    name: item.name,
                    friend: (item.friend == null ? -1 : item.friend.id)
                };
            }
        }
    }
);