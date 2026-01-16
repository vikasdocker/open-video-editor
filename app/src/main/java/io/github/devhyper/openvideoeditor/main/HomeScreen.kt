
@file:OptIn(ExperimentalMaterial3Api::class)

package io.github.devhyper.openvideoeditor.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.ClosedCaption
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.FaceRetouchingNatural
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.devhyper.openvideoeditor.ui.theme.OpenVideoEditorTheme

@Composable
fun HomeScreen(onNewVideoClick: () -> Unit) {
    Scaffold(
        topBar = { HomeAppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            NewProjectSection(onNewVideoClick = onNewVideoClick)
            Spacer(modifier = Modifier.height(24.dp))
            FeatureGrid()
            Spacer(modifier = Modifier.height(24.dp))
            ProjectsSection()
        }
    }
}

@Composable
fun HomeAppBar() {
    TopAppBar(
        title = {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}

@Composable
fun NewProjectSection(onNewVideoClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        NewProjectCard(
            title = "New Video",
            icon = Icons.Default.Videocam,
            modifier = Modifier.weight(1f),
            onClick = onNewVideoClick
        )
        NewProjectCard(
            title = "Edit Photo",
            icon = Icons.Default.Photo,
            modifier = Modifier.weight(1f),
            onClick = { /* TODO */ }
        )
    }
}

@Composable
fun NewProjectCard(
    title: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = title, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun FeatureGrid() {
    val features = listOf(
        "AutoCut" to Icons.Default.ContentCut,
        "Retouch" to Icons.Default.FaceRetouchingNatural,
        "Auto captions" to Icons.Default.ClosedCaption,
        "Remove background" to Icons.Default.AutoAwesome,
        "Camera" to Icons.Default.Camera,
        "Teleprompter" to Icons.Default.Videocam, // Placeholder
        "Adjust speed" to Icons.Default.Speed,
        "All tools" to Icons.Default.MoreHoriz
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier.height(200.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(features) { (name, icon) ->
            FeatureItem(name = name, icon = icon)
        }
    }
}

@Composable
fun FeatureItem(name: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(12.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(imageVector = icon, contentDescription = name, tint = MaterialTheme.colorScheme.onSecondaryContainer)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = name, fontSize = 12.sp, textAlign = TextAlign.Center, maxLines = 2)
    }
}


@Composable
fun ProjectsSection() {
    Column {
        Text(
            text = "Projects",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProjectCard(modifier = Modifier.weight(1f))
            ProjectCard(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ProjectCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.height(150.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Placeholder for thumbnail
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            )
            // Duration text
            Text(
                text = "00:15",
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    OpenVideoEditorTheme {
        HomeScreen(onNewVideoClick = {})
    }
}
