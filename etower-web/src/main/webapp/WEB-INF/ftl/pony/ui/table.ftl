<#--
表格标签：用于显示列表数据。
	value：列表数据，可以是Pagination也可以是List。
	class：table的class样式。默认"pn-ltable"。
	sytle：table的style样式。默认""。
	width：表格的宽度。默认100%。
-->
<#macro table value listAction="v_list.do" width="100%">
<table width="${width}" cellspacing="0">
<#if value?is_sequence><#local pageList=value/><#else><#local pageList=value.content/></#if>
<#list pageList as row>
<#if row_index==0>
<#assign i=-1/>
<thead><tr><#nested row,i,true/></tr></thead>
</#if>
<#assign i=row_index has_next=row_has_next/>
<#if row_index==0><tbody><tr><#else><tr></#if><#nested row,row_index,row_has_next/>
<#if !row_has_next>
</tr></tbody>
<#else>
</tr>
</#if>
</#list>
</table>
<#if !value?is_sequence>
<div class="btn_wrap_fixed">
	<label class="select_all"><input type="checkbox" name="checkall" class="J_checkall">全选/取消</label>
    <input type="button" class="btn" data-tdtype="batch_action" data-acttype="ajax" data-uri="o_delete.do" data-name="ids" data-msg="<@s.m "global.confirm.delete"/>" value="<@s.m "global.delete"/>" />
	<div id="pages">
		共 ${value.getTotalElements()} 条记录&nbsp;
		每页<input type="text" value="${value.getSize()}" style="width:30px" onfocus="this.select();" onblur="$.cookie('_cookie_page_size',this.value,{expires:3650});" onkeypress="if(event.keyCode==13){$(this).blur();return false;}"/>条&nbsp;
		<input class="btn" type="button" value="首 页" onclick="_gotoPage('1');"<#if value.getNumber() == 0> disabled="disabled"</#if>/>
		<input class="btn" type="button" value="上一页" onclick="_gotoPage('${value.getNumber()}');"<#if value.getNumber() == 0> disabled="disabled"</#if>/>
		<input class="btn" type="button" value="下一页" onclick="_gotoPage('${value.getNumber()+2}');"<#if value.lastPage> disabled="disabled"</#if>/>
		<input class="btn" type="button" value="尾 页" onclick="_gotoPage('${value.getTotalPages()}');"<#if value.lastPage> disabled="disabled"</#if>/>&nbsp;
		当前 ${value.getNumber()+1}/${value.getTotalPages()} 页 &nbsp;转到第<input type="text" id="_goPs" style="width:50px" onfocus="this.select();" onkeypress="if(event.keyCode==13){$('#_goPage').click();return false;}"/>页
		<input class="btn" id="_goPage" type="button" value="转" onclick="_gotoPage($('#_goPs').val());"<#if value.getNumber() == 0> disabled="disabled"</#if>/>
	</div>
</div>
<script type="text/javascript">
function getTableForm() {
	return document.getElementById('tableForm');
}
function _gotoPage(pageNo) {
	try{
		var tableForm = getTableForm();
		$("input[name=pageNo]").val(pageNo);
		tableForm.action="${listAction}";
		tableForm.onsubmit=null;
		tableForm.submit();
	} catch(e) {
		alert('_gotoPage(pageNo)方法出错');
	}
}
</script>
</#if>
</#macro>