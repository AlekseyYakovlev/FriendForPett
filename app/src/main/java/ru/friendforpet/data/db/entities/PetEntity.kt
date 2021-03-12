package ru.friendforpet.data.db.entities

class PetEntity (
    val _id: Int,
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
)