<!DOCTYPE html>
<html>
<head>
    <title>custom-toolbar</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  
    <link rel="stylesheet" href="css/examples.css">
    <style>
        .w70 {width: 70px !important;}
    </style>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h1>Custom Toolbar</h1>
        <p>Use <code>toolbar</code> option to define the custom toolbars.</p>
        <div id="toolbar">
            <div class="form-inline" role="form">
                <div class="form-group">
                    <span>Offset: </span>
                    <input name="offset" class="form-control w70" type="number" value="0">
                </div>
                <div class="form-group">
                    <span>Limit: </span>
                    <input name="limit" class="form-control w70" type="number" value="5">
                </div>
                <div class="form-group">
                    <input name="search" class="form-control" type="text" placeholder="Search">
                </div>
                <button id="ok" type="submit" class="btn btn-default">OK</button>
            </div>
        </div>
        <table id="table"
            data-toggle="table"
            data-url="data/data1.json"
            data-search="true"
            data-show-refresh="true"
            data-show-toggle="true"
            data-show-columns="true"
            data-toolbar="#toolbar"
            data-query-params="queryParams"
            data-pagination="true"
            data-halign="center"
            >
            <thead>
            <tr>
                <th data-field="id">ID</th>
                <th data-field="name">Item Name</th>
                <th data-field="price">Item Price</th>
				<th data-field="amount">amount</th>
            </tr>
            </thead>
        </table>
    </div>

<script>
    var $table = $('#table'),
        $ok = $('#ok');

    $(function () {
        $ok.click(function () {
            $table.bootstrapTable('refresh');
        });
    });

    function queryParams() {
        var params = {};
        $('#toolbar').find('input[name]').each(function () {
            params[$(this).attr('name')] = $(this).val();
        });
        return params;
    }
</script>
</body>
</html>