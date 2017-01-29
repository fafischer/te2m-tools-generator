<#macro name counter>

	<#assign ctnr = counter-1>
	<#if (ctnr > 0)>
		${ctnr}
		<@name counter=ctnr />
	</#if>

</#macro>

<@name counter=10 />