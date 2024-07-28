package com.example.machinetestdemo.model
import org.simpleframework.xml.*

@Root(name = "feed", strict = false)
data class Feed(

    @field:Element(name = "id")
    var id: String = "",

    @field:ElementList(name = "entry" , inline = true)
    var entries: List<Entry> = mutableListOf()

)

@Root(name = "entry", strict = false)
data class Entry(
   @field:Element(name = "title")
    var title: String? = "",

   @field:Element(name = "rights")
   var rights: String? = "",

  @field:ElementList(name = "image", inline = true)
    var images: List<Image> = mutableListOf(),

    @field:Element(name = "artist")
    var artist: Artist? = null,

    @field:Element(name = "content")
        var content: Content? = null,

       @field:Element(name = "price")
       var price: Price? = null,

   @field:ElementList(name = "link", inline = true)
   var link: List<Link>? = mutableListOf()
)

@Root(name = "artist", strict = false)
data class Artist(
    @field:Text
    var artist: String = ""
)

@Root(name = "image", strict = false)
data class Image(
  @field:Text
    var url: String = ""
)

@Root(name = "content", strict = false)
data class Content(
    @field:Text
    var content: String = ""
)
@Root(name = "price", strict = false)
data class Price(
    @field:Text
    var price: String = ""
)

@Root(name = "link", strict = false)
data class Link @JvmOverloads constructor(
    @field:Attribute(name = "rel")
    var rel: String? = null,

    @field:Attribute(name = "type")
    var type: String? = null,

    @field:Attribute(name = "href")
    var href: String? = null
)