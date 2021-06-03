package com.example.nestedjson



data class NestedJSONModel(var data: List<Data>)

data class Data(var id: String?,
                var images: Images?)

data class Images(var original : Original)

data class Original(var url: String)