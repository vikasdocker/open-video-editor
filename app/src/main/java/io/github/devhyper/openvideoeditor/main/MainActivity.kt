package io.github.devhyper.openvideoeditor.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import io.github.devhyper.openvideoeditor.misc.setImmersiveMode
import io.github.devhyper.openvideoeditor.misc.setupSystemUi
import io.github.devhyper.openvideoeditor.settings.SettingsDataStore
import io.github.devhyper.openvideoeditor.ui.theme.OpenVideoEditorTheme
import io.github.devhyper.openvideoeditor.videoeditor.VideoEditorActivity

class MainActivity : ComponentActivity() {
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    private lateinit var pickProject: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSystemUi()

        val dataStore = SettingsDataStore(this)
        pickMedia =
            registerForActivityResult(CustomPickVisualMedia { dataStore.getLegacyFilePickerBlocking() }) { uri ->
                if (uri != null) {
                    launchVideoEditor(uri)
                }
            }
        pickProject = registerForActivityResult(
            CustomOpenDocument()
        ) { uri ->
            if (uri != null) {
                launchVideoEditor(uri)
            }
        }

        setContent {
            OpenVideoEditorTheme {
                setImmersiveMode(false)
                HomeScreen(onNewVideoClick = {
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
                })
            }
        }
    }

    private fun launchVideoEditor(uri: Uri) {
        val intent = Intent(this, VideoEditorActivity::class.java)
        intent.action = Intent.ACTION_EDIT
        intent.data = uri
        startActivity(intent)
    }
}
