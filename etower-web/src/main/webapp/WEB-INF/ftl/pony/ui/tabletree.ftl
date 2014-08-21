<#--
表格标签：用于显示列表数据。
	value：列表数据，可以是Pagination也可以是List。
	class：table的class样式。默认"pn-ltable"。
	sytle：table的style样式。默认""。
	width：表格的宽度。默认100%。
-->
<#macro tabletree value listAction="v_list.do" width="100%">
<table width="${width}" cellspacing="0" id="J_cate_tree">
<#if value?is_sequence><#local pageList=value/><#else><#local pageList=value.content/></#if>
<#list pageList as row>
<#if row_index==0>
<#assign i=-1/>
<thead><tr><#nested row,i,true/></tr></thead>
</#if>
<#assign i=row_index has_next=row_has_next/>
<#assign parentid=row.getParentId()/>
<#if row_index==0><tbody><tr id='node-${row.getId()}'><#else><#if parentid=0><tr id='node-${row.getId()}'><#else><tr id='node-${row.getId()}' class="child-of-node-${row.getParentId()}"></#if></#if><#nested row,row_index,row_has_next/>
<#if !row_has_next>
</tr></tbody>
<#else>
</tr>
</#if>
</#list>
</table>
</#macro>