package com.uphar.bussiness.domain


import com.uphar.bussiness.domain.Utils.EntityMapper
import com.uphar.bussiness.domain.data.BackupDetails
import com.uphar.bussiness.domain.data.Coverage
import com.uphar.bussiness.domain.data.ImageResponse
import com.uphar.bussiness.domain.data.Thumbnail
import com.uphar.network.CoverageNetwork
import com.uphar.network.ImageResponseNetwork
import javax.inject.Inject

class ImageMapper @Inject constructor() : EntityMapper<ImageResponse, List<CoverageNetwork>> {
    override fun mapFromEntity(entity: ImageResponse): List<CoverageNetwork> {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: List<CoverageNetwork>): ImageResponse {
        return ImageResponse(
            response = domainModel.map { coverage ->
                Coverage(
                    id = coverage.id ?: "",
                    title = coverage.title ?: "",
                    language = coverage.language ?: "",
                    thumbnail = Thumbnail(
                        id = coverage.thumbnail?.id ?: "",
                        version = coverage.thumbnail?.version ?: 0,
                        domain = coverage.thumbnail?.domain ?: "",
                        basePath = coverage.thumbnail?.basePath ?: "",
                        key = coverage.thumbnail?.key ?: "",
                        qualities = coverage.thumbnail?.qualities ?: emptyList(),
                        aspectRatio = coverage.thumbnail?.aspectRatio ?: 0
                    ),
                    mediaType = coverage.mediaType ?: 0,
                    coverageURL = coverage.coverageURL ?: "",
                    publishedAt = coverage.publishedAt ?: "",
                    publishedBy = coverage.publishedBy ?: "",
                    backupDetails = BackupDetails(
                        pdfLink = coverage.backupDetails?.pdfLink ?: "",
                        screenshotURL = coverage.backupDetails?.screenshotURL ?: ""
                    )
                )
            }
        )
    }



}