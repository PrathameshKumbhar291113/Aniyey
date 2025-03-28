package com.prathameshkumbhar.aniyey.connection.models


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class GetAnimeDetailsByIdResponse(
    @SerializedName("data")
    var `data`: Data?
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("aired")
        var aired: Aired?,
        @SerializedName("airing")
        var airing: Boolean?, // false
        @SerializedName("approved")
        var approved: Boolean?, // true
        @SerializedName("background")
        var background: String?, // Sousou no Frieren was released on Blu-ray and DVD in seven volumes from January 24, 2024, to July 17, 2024.
        @SerializedName("broadcast")
        var broadcast: Broadcast?,
        @SerializedName("demographics")
        var demographics: List<Demographic?>?,
        @SerializedName("duration")
        var duration: String?, // 24 min per ep
        @SerializedName("episodes")
        var episodes: Int?, // 28
        @SerializedName("explicit_genres")
        var explicitGenres: List<String?>?,
        @SerializedName("favorites")
        var favorites: Int?, // 65268
        @SerializedName("genres")
        var genres: List<Genre?>?,
        @SerializedName("images")
        var images: Images?,
        @SerializedName("licensors")
        var licensors: List<Licensor?>?,
        @SerializedName("mal_id")
        var malId: Int?, // 52991
        @SerializedName("members")
        var members: Int?, // 1063000
        @SerializedName("popularity")
        var popularity: Int?, // 156
        @SerializedName("producers")
        var producers: List<Producer?>?,
        @SerializedName("rank")
        var rank: Int?, // 1
        @SerializedName("rating")
        var rating: String?, // PG-13 - Teens 13 or older
        @SerializedName("score")
        var score: Double?, // 9.31
        @SerializedName("scored_by")
        var scoredBy: Int?, // 622645
        @SerializedName("season")
        var season: String?, // fall
        @SerializedName("source")
        var source: String?, // Manga
        @SerializedName("status")
        var status: String?, // Finished Airing
        @SerializedName("studios")
        var studios: List<Studio?>?,
        @SerializedName("synopsis")
        var synopsis: String?, // During their decade-long quest to defeat the Demon King, the members of the hero's party—Himmel himself, the priest Heiter, the dwarf warrior Eisen, and the elven mage Frieren—forge bonds through adventures and battles, creating unforgettable precious memories for most of them.However, the time that Frieren spends with her comrades is equivalent to merely a fraction of her life, which has lasted over a thousand years. When the party disbands after their victory, Frieren casually returns to her "usual" routine of collecting spells across the continent. Due to her different sense of time, she seemingly holds no strong feelings toward the experiences she went through.As the years pass, Frieren gradually realizes how her days in the hero's party truly impacted her. Witnessing the deaths of two of her former companions, Frieren begins to regret having taken their presence for granted; she vows to better understand humans and create real personal connections. Although the story of that once memorable journey has long ended, a new tale is about to begin.[Written by MAL Rewrite]
        @SerializedName("themes")
        var themes: List<String?>?,
        @SerializedName("title")
        var title: String?, // Sousou no Frieren
        @SerializedName("title_english")
        var titleEnglish: String?, // Frieren: Beyond Journey's End
        @SerializedName("title_japanese")
        var titleJapanese: String?, // 葬送のフリーレン
        @SerializedName("title_synonyms")
        var titleSynonyms: List<String?>?,
        @SerializedName("titles")
        var titles: List<Title?>?,
        @SerializedName("trailer")
        var trailer: Trailer?,
        @SerializedName("type")
        var type: String?, // TV
        @SerializedName("url")
        var url: String?, // https://myanimelist.net/anime/52991/Sousou_no_Frieren
        @SerializedName("year")
        var year: Int? // 2023
    ) : Parcelable {
        @Parcelize
        data class Aired(
            @SerializedName("from")
            var from: String?, // 2023-09-29T00:00:00+00:00
            @SerializedName("prop")
            var prop: Prop?,
            @SerializedName("string")
            var string: String?, // Sep 29, 2023 to Mar 22, 2024
            @SerializedName("to")
            var to: String? // 2024-03-22T00:00:00+00:00
        ) : Parcelable {
            @Parcelize
            data class Prop(
                @SerializedName("from")
                var from: From?,
                @SerializedName("to")
                var to: To?
            ) : Parcelable {
                @Parcelize
                data class From(
                    @SerializedName("day")
                    var day: Int?, // 29
                    @SerializedName("month")
                    var month: Int?, // 9
                    @SerializedName("year")
                    var year: Int? // 2023
                ) : Parcelable

                @Parcelize
                data class To(
                    @SerializedName("day")
                    var day: Int?, // 22
                    @SerializedName("month")
                    var month: Int?, // 3
                    @SerializedName("year")
                    var year: Int? // 2024
                ) : Parcelable
            }
        }

        @Parcelize
        data class Broadcast(
            @SerializedName("day")
            var day: String?, // Fridays
            @SerializedName("string")
            var string: String?, // Fridays at 23:00 (JST)
            @SerializedName("time")
            var time: String?, // 23:00
            @SerializedName("timezone")
            var timezone: String? // Asia/Tokyo
        ) : Parcelable

        @Parcelize
        data class Demographic(
            @SerializedName("mal_id")
            var malId: Int?, // 27
            @SerializedName("name")
            var name: String?, // Shounen
            @SerializedName("type")
            var type: String?, // anime
            @SerializedName("url")
            var url: String? // https://myanimelist.net/anime/genre/27/Shounen
        ) : Parcelable

        @Parcelize
        data class Genre(
            @SerializedName("mal_id")
            var malId: Int?, // 2
            @SerializedName("name")
            var name: String?, // Adventure
            @SerializedName("type")
            var type: String?, // anime
            @SerializedName("url")
            var url: String? // https://myanimelist.net/anime/genre/2/Adventure
        ) : Parcelable

        @Parcelize
        data class Images(
            @SerializedName("jpg")
            var jpg: Jpg?,
            @SerializedName("webp")
            var webp: Webp?
        ) : Parcelable {
            @Parcelize
            data class Jpg(
                @SerializedName("image_url")
                var imageUrl: String?, // https://cdn.myanimelist.net/images/anime/1015/138006.jpg
                @SerializedName("large_image_url")
                var largeImageUrl: String?, // https://cdn.myanimelist.net/images/anime/1015/138006l.jpg
                @SerializedName("small_image_url")
                var smallImageUrl: String? // https://cdn.myanimelist.net/images/anime/1015/138006t.jpg
            ) : Parcelable

            @Parcelize
            data class Webp(
                @SerializedName("image_url")
                var imageUrl: String?, // https://cdn.myanimelist.net/images/anime/1015/138006.webp
                @SerializedName("large_image_url")
                var largeImageUrl: String?, // https://cdn.myanimelist.net/images/anime/1015/138006l.webp
                @SerializedName("small_image_url")
                var smallImageUrl: String? // https://cdn.myanimelist.net/images/anime/1015/138006t.webp
            ) : Parcelable
        }

        @Parcelize
        data class Licensor(
            @SerializedName("mal_id")
            var malId: Int?, // 1468
            @SerializedName("name")
            var name: String?, // Crunchyroll
            @SerializedName("type")
            var type: String?, // anime
            @SerializedName("url")
            var url: String? // https://myanimelist.net/anime/producer/1468/Crunchyroll
        ) : Parcelable

        @Parcelize
        data class Producer(
            @SerializedName("mal_id")
            var malId: Int?, // 17
            @SerializedName("name")
            var name: String?, // Aniplex
            @SerializedName("type")
            var type: String?, // anime
            @SerializedName("url")
            var url: String? // https://myanimelist.net/anime/producer/17/Aniplex
        ) : Parcelable

        @Parcelize
        data class Studio(
            @SerializedName("mal_id")
            var malId: Int?, // 11
            @SerializedName("name")
            var name: String?, // Madhouse
            @SerializedName("type")
            var type: String?, // anime
            @SerializedName("url")
            var url: String? // https://myanimelist.net/anime/producer/11/Madhouse
        ) : Parcelable

        @Parcelize
        data class Title(
            @SerializedName("title")
            var title: String?, // Sousou no Frieren
            @SerializedName("type")
            var type: String? // Default
        ) : Parcelable

        @Parcelize
        data class Trailer(
            @SerializedName("embed_url")
            var embedUrl: String?, // https://www.youtube.com/embed/ZEkwCGJ3o7M?enablejsapi=1&wmode=opaque&autoplay=1
            @SerializedName("images")
            var images: Images?,
            @SerializedName("url")
            var url: String?, // https://www.youtube.com/watch?v=ZEkwCGJ3o7M
            @SerializedName("youtube_id")
            var youtubeId: String? // ZEkwCGJ3o7M
        ) : Parcelable {
            @Parcelize
            data class Images(
                @SerializedName("image_url")
                var imageUrl: String?, // https://img.youtube.com/vi/ZEkwCGJ3o7M/default.jpg
                @SerializedName("large_image_url")
                var largeImageUrl: String?, // https://img.youtube.com/vi/ZEkwCGJ3o7M/hqdefault.jpg
                @SerializedName("maximum_image_url")
                var maximumImageUrl: String?, // https://img.youtube.com/vi/ZEkwCGJ3o7M/maxresdefault.jpg
                @SerializedName("medium_image_url")
                var mediumImageUrl: String?, // https://img.youtube.com/vi/ZEkwCGJ3o7M/mqdefault.jpg
                @SerializedName("small_image_url")
                var smallImageUrl: String? // https://img.youtube.com/vi/ZEkwCGJ3o7M/sddefault.jpg
            ) : Parcelable
        }
    }
}