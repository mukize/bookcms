<#macro guest title hideHeading=false>
  <!DOCTYPE html>
  <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="css/main-compiled.css">
      <script src="/js/htmx.min.js"></script>
      <title>
        ${title} | BookCMS
      </title>
    </head>

    <body class="flex flex-col min-h-screen">
      <header class="px-4 py-2 border-b-2 border-amber-700 bg-amber-300">
        <div class="flex justify-between align-middle">
          <a href="/">
            <img
              src="images/logo-placeholder-round.png"
              alt="BookCMS logo"
              class="w-20 h-20" />
          </a>
          <nav class="flex items-center gap-4" hx-boost="true">
            <a href="/" class="${title?matches('Home')?then('text-blue-700', '')} transition-colors hover:text-cyan-600">
              Home
            </a>
            <a href="/books" class="${title?matches('Books')?then('text-blue-700', '')} transition-colors hover:text-cyan-600">
              Books
            </a>
            <a href="/about" class="${title?matches('About')?then('text-blue-700', '')} transition-colors hover:text-cyan-600">
              About
            </a>
            <a href="/contact" class="${title?matches('Contact')?then('text-blue-700', '')} transition-colors hover:text-cyan-600">
              Contact
            </a>
            <@c.button class="border-black bg-cyan-500 hover:bg-cyan-300">
              Cart
            </@c.button>
          </nav>
        </div>
        <#if (!hideHeading)>
          <div class="flex justify-center">
            <h1 class="text-5xl font-extralight">
              ${title}
            </h1>
          </div>
        </#if>
      </header>
      <main class="px-10 py-6 grow">
        <#nested />
      </main>
      <footer class="flex justify-center h-20 my-auto align-middle bg-amber-400">
        <p class="m-auto">Made with &hearts; @ 2023</p>
      </footer>
    </body>
  </html>
</#macro>