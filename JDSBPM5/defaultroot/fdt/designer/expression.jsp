<%@ page contentType="text/html; charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript" src="<c:url value='/scripts/app/magic.js'/>"></script>
<script type="text/javascript">

    <s:iterator value="expression.updateValue" status="routs">
    fillValue('<s:property value="key"/>', '<s:property value="value" escape="false"/>');
    </s:iterator>
    function fillValue(id, value) {
        try {
            var cell = $(id);
            cell = windowf.cells4(cell);
            cell.setValue(value);
        } catch(ex) {
        }
    }

    //- is need clear magic-div
    if (isMagicDoneNeed()) {
        magicDone();
    }
    //- is need do save after loaded
    if (isSaveNeed()) {
        startSave();
    }
    //- is need do another load after loaded
    if (isAnotherLoadNeed()) {
        startLoad();
    }
</script>