<%@ page language="java" errorPage="/error.jsp" pageEncoding="utf8" %>
<%
String contextpath = request.getContextPath() + "/";
%>
<html>
<head>
    <title>Custom Fields</title>
  
    <link rel="stylesheet" type="text/css" href="../resources/css/ext-all.css" />
    <script type="text/javascript" src="../js/ext-base.js"></script>
    <script type="text/javascript" src="../js/ext-all.js"></script>
    <script type="text/javascript" src="js/SearchField.js"></script>
    <script type="text/javascript" src="js/usergroupQuery.js"></script>

    <style type="text/css">
        #search-results a {
            color: #385F95;
            font:bold 11px tahoma, arial, helvetica, sans-serif;
            text-decoration:none;
        }
        #search-results a:hover {
            text-decoration:underline;
        }
        #search-results .search-item {
            padding:5px;
        }
        #search-results p {
            margin:3px !important;
        }
        #search-results {
            border-bottom:1px solid #ddd;
            margin: 0 1px;
            height:300px;
            overflow:auto;
        }
        #search-results .x-toolbar {
            border:0 none;
        }
    </style>

    <!-- Common Styles for the examples -->
    <link rel="stylesheet" type="text/css" href="examples.css" />
</head>
<body>
<script type="text/javascript" src="<%=contextpath%>usm/resources/examples.js"></script><!-- EXAMPLES -->

<div style="width:600px;" id="search-panel">
</div>

</body>
</html>
