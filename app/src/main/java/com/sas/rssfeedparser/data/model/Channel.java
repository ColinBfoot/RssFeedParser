package com.sas.rssfeedparser.data.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.List;

@NamespaceList({
        @Namespace(reference = "http://www.w3.org/2005/Atom", prefix = "atom")
})
@Root(strict = false)
public class Channel {
    @ElementList(entry = "link", inline = true, required = false)
    public List<Link> links;

    @ElementList(name = "item", inline = true)
    public List<Item> itemList;


    @Element
    String title;
    @Element
    String language;

    @Element(name = "ttl", required = false)
    int ttl;

    @Element(name = "pubDate", required = false)
    String pubDate;

    @Override
    public String toString() {
        return "Channel{" +
                "links=" + links +
                ", itemList=" + itemList +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", ttl=" + ttl +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }

    public static class Link {
        @Attribute(required = false)
        public String href;

        @Attribute(required = false)
        public String rel;

        @Attribute(name = "type", required = false)
        public String contentType;

        @Text(required = false)
        public String link;
    }

    @Root(name = "item", strict = false)
    public static class Item {

        @Element(name = "title")
        public String title;
        @Element(name = "link")
        public String link;
        @Element(name = "guid", required = false)
        public String guid;
        @Element(name = "pubDate", required = false)
        public String pubDate;
        @Element(name = "thumbnail", required = false)
        public Thumbnail thumbnail;
        @Element(name = "content", required = false)
        public Content content;

        @Override
        public String toString() {
            return "Item{" +
                    "title='" + title + '\'' +
                    ", link='" + link + '\'' +
                    ", guid='" + guid + '\'' +
                    ", pubDate='" + pubDate + '\'' +
                    ", thumbnail='" + thumbnail + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }

    public static class Content {

        @Attribute(required = false)
        public String url;

        @Attribute(required = false)
        public String type;
    }

    public static class Thumbnail {

        @Attribute(required = false)
        public String url;

        @Attribute(required = false)
        public int height;

        @Attribute(required = false)
        public int width;
    }
}
