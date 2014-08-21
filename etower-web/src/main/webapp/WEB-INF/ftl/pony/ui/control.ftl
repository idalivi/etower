<#--
label：有label代表需要创建td和tr。
colspan：有colspan代表不需要创建tr。
labelWidth：需要form传递。
-->
<#if label!="">
<th width="${labelWidth}%"><@s.mt code=label text=label/><#if hasColon="true">${colon}</#if></th><#rt/>
<td<#if colspan!=""> colspan="${colspan?number*2-1}"</#if> width="${width?number-labelWidth?number}%"><#rt/>
</#if>
<#if help!="" && helpPosition=='1'><span class="gray ml10"><@s.mt code=help text=help/></span></#if>