<@layout.guest title="Home">
  <section class="flex justify-between gap-5">
    <@c.article title="Lorem Ipsum" class="w-3/5">
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptate architecto
        reiciendis nihil pariatur labore repellat sequi porro placeat molestiae vitae nisi, inventore,
        autem, odit odio corrupti laudantium mollitia debitis unde.
      </p>
    </@c.article>
    <img src="/images/placeholder.webp" alt="Portrait" class="p-2 border w-80 rounded-2xl bg-slate-100" />
  </section>
  <section class="flex justify-between gap-5 mt-5">
    <@c.article title="My Books" class="w-1/2 bg-teal-200 border rounded-2xl" padded=true>
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptate architecto
        reiciendis nihil pariatur labore repellat sequi porro placeat molestiae vitae nisi, inventore,
        autem, odit odio corrupti laudantium mollitia debitis unde.
      </p>
      <a href="/books">
        <@c.button class="bg-emerald-500 border-emerald-600 hover:bg-emerald-300">
          View Books
        </@c.button>
      </a>
    </@c.article>
    <@c.article title="Contact Me!" class="w-1/2 bg-teal-200 border rounded-2xl" padded=true>
      <p>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptate architecto
        reiciendis nihil pariatur labore repellat sequi porro placeat molestiae vitae nisi, inventore,
        autem, odit odio corrupti laudantium mollitia debitis unde.
      </p>
      <a href="/contact">
        <@c.button class="bg-emerald-500 border-emerald-600 hover:bg-emerald-300">
          Contact
        </@c.button>
      </a>
    </@c.article>
  </section>
</@layout.guest>