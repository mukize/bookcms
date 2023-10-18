<@layout.guest title="Books" hideHeading=true> 
  <#list books>
    <section class="grid grid-cols-3 gap-y-10 gap-x-10">
      <#items as book>
        <article class="flex flex-col items-center justify-center gap-2">
          <#if book.image??>
            <img 
              src="${book.image}" 
              alt="Image of a Book" 
              class="w-64 p-2 border h-72 rounded-2xl bg-slate-100" 
            >
          <#else>
            <img 
              src="/images/placeholder.webp" 
              alt="Place holder image" 
              class="w-64 p-2 border h-72 rounded-2xl bg-slate-100" 
            >
          </#if>
          <h2 class="text-2xl">${book.title}</h2>
          <h3 class="text-xl">${book.price}</h3>
          <a href="/books/${book.id}" hx-boost="true" class="w-full">
            <@c.button class="w-full bg-teal-400 hover:bg-teal-200">
              View More
            </@c.button>
          </a>
        </article>
      </#items>
    </section>
  <#else> 
    <p class="flex justify-center p-4 bg-teal-100 border">
      Currently no books available. 
    </p>
  </#list>
</@layout.guest>