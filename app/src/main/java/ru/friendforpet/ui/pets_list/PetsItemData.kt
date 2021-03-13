package ru.friendforpet.ui.pets_list

import ru.friendforpet.ui.base.RvItemData

class PetsItemData(
    override val _id: Int,
    val name: String,
    val sex: String,
    val age: Int,
    val location: String,
    val size: String,
    val personality: String,
    val hair: String,
    val color: String,
    val description: String,
    val tags: List<String>,
    val addedDate: String,
    val photo: String,
    val isLiked: Boolean,
    val type: String,
) : RvItemData