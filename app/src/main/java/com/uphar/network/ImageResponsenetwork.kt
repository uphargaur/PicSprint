package com.uphar.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageResponseNetwork(
    @SerializedName("response")
    @Expose
    val response: List<CoverageNetwork>
)

data class CoverageNetwork(
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("language")
    @Expose
    val language: String?,
    @SerializedName("thumbnail")
    @Expose
    val thumbnail: ThumbnailNetwork?,
    @SerializedName("mediaType")
    @Expose
    val mediaType: Int?,
    @SerializedName("coverageURL")
    @Expose
    val coverageURL: String?,
    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String?,
    @SerializedName("publishedBy")
    @Expose
    val publishedBy: String?,
    @SerializedName("backupDetails")
    @Expose
    val backupDetails: BackupDetailsNetwork?
)

data class ThumbnailNetwork(
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("version")
    @Expose
    val version: Int?,
    @SerializedName("domain")
    @Expose
    val domain: String?,
    @SerializedName("basePath")
    @Expose
    val basePath: String?,
    @SerializedName("key")
    @Expose
    val key: String?,
    @SerializedName("qualities")
    @Expose
    val qualities: List<Int>?,
    @SerializedName("aspectRatio")
    @Expose
    val aspectRatio: Int?
)

data class BackupDetailsNetwork(
    @SerializedName("pdfLink")
    @Expose
    val pdfLink: String?,
    @SerializedName("screenshotURL")
    @Expose
    val screenshotURL: String?
)
