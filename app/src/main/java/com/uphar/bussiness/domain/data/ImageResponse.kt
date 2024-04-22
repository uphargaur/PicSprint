package com.uphar.bussiness.domain.data


data class ImageResponse(
    val response: List<Coverage>
)
data class Coverage(
    val id: String,
    val title: String,
    val language: String,
    val thumbnail: Thumbnail,
    val mediaType: Int,
    val coverageURL: String,
    val publishedAt: String,
    val publishedBy: String,
    val backupDetails: BackupDetails
)

data class Thumbnail(
    val id: String,
    val version: Int,
    val domain: String,
    val basePath: String,
    val key: String,
    val qualities: List<Int>,
    val aspectRatio: Int
)

data class BackupDetails(
    val pdfLink: String,
    val screenshotURL: String
)

