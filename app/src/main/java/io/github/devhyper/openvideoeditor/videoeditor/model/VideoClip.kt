package io.github.devhyper.openvideoeditor.videoeditor.model

import android.net.Uri
import java.util.UUID

/**
 * Represents a single, editable segment of video on the timeline.
 *
 * @param id A unique identifier for the clip.
 * @param sourceUri The URI of the source video file.
 * @param startTimeInSourceMs The start time of the clip within the original source video, in milliseconds.
 * @param endTimeInSourceMs The end time of the clip within the original source video, in milliseconds.
 * @param timelinePositionMs The position of this clip on the main timeline, in milliseconds.
 * @param durationMs The duration of the clip after trimming.
 */
data class VideoClip(
    val id: String = UUID.randomUUID().toString(),
    val sourceUri: Uri,
    val startTimeInSourceMs: Long,
    val endTimeInSourceMs: Long,
    val timelinePositionMs: Long,
    val durationMs: Long = endTimeInSourceMs - startTimeInSourceMs
)
