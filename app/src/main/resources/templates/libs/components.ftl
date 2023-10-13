<#macro article title class="" padded=false>
  <article class="${class} flex flex-col gap-4 ${padded?then('px-5 py-3','')}">
    <h2 class="text-3xl">
      ${title}
    </h2>
    <#nested />
  </article>
</#macro>
<#macro button class="" attributes...>
  <button
    class="${class} px-4 py-2 transition-colors border rounded-md"
    <#list attributes as k, v>
    ${k}="${v}"
    </#list>
    >
    <#nested />
  </button>
</#macro>