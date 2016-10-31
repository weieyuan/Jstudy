####Web Client
**Http信息查看工具**
* Firebug
* HttpFox
**浏览器缓存**
* CTRL + F5一定会请求到最新的页面数据，不会使用浏览器中的缓存和请求到服务器端的缓存。
> 这是因为在请求头中增加了Pragma:no-cache和Cache-control:no-cache的参数
* Cache-control的可选参数
	* Public 所有内容都将缓存，在响应头中设置
	* Private 内容缓存到私有缓存中，响应头设置
	* no-cache 不缓存，请求头和响应头设置
	* no-store 不会缓存到缓存或internet的临时文件中，响应头设置
	* must-revalidation/proxy-revalidation 如果缓存内容失效，请求必须发送到服务器端重新验证，请求头设置
	* max-age 缓存内容在多少秒后失效，和Last-Modified一起使用时优先级较高响应头中设置
* Expires
> 浏览器请求之前检查这个参数，判断页面是否过期，如果是就向服务器端请求
* Last-Modified
> 浏览器再次请求时会在请求头中增加If-Modified-Since: [时间]，询问当前页面是否是最新的，如果是服务端返回304
*max-age,Expires,Last-Modified的区别*
