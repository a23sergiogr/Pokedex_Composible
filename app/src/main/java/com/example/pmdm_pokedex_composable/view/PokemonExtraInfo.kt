package com.example.pmdm_pokedex_composable.view

import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun PlaySoundButton(
    audioUrl: String // URL del archivo OGG
) {
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }


        Button(
            colors = ButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                disabledContainerColor = darkenColor(MaterialTheme.colorScheme.secondary, 0.2f),
                contentColor = MaterialTheme.colorScheme.onSecondary,
                disabledContentColor = MaterialTheme.colorScheme.onSecondary
            ),
            onClick = {
                try {
                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(audioUrl)
                    mediaPlayer.prepareAsync()
                    mediaPlayer.setOnPreparedListener { it.start() }
                } catch (e: Exception) {
                    Toast.makeText(context, "Error al reproducir el audio", Toast.LENGTH_SHORT).show()
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Audiotrack,
                contentDescription = "Reproducir audio"
            )
        }
    }

