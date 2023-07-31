package com.moling.micatoolkit.presentation.model

class DetailItem(title: String, content: String, route: String?) {
    val title: String
    val content: String
    val route: String?

    init {
        this.title = title
        this.content = content
        this.route = route
    }
}