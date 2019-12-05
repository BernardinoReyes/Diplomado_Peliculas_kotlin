package com.breyes.peliculaskotlin.base

import com.breyes.peliculaskotlin.data.model.Movie

class BaseApiModel<T> {

    var page: Int = 0
    var total_results: String? = null
    var total_pages: String? = null
    var results: List<Movie>? = null

}
