/*
 * Copyright 2023 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.google.samples.apps.nowinandroid.feature.interests

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun InterestsRoute(
    shouldShowTwoPane: Boolean,
    onTopicClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InterestsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.interestUiState.collectAsStateWithLifecycle()
    val topicUiState by viewModel.topicUiState.collectAsStateWithLifecycle()

    Row(modifier = modifier) {
        if(shouldShowTwoPane || topicUiState == null) {
            InterestsScreen(
                uiState = uiState,
                followTopic = viewModel::followTopic,
                onTopicClick = onTopicClick,
                modifier = Modifier.weight(1f),
            )
        }

        topicUiState?.let { topic ->
            TopicScreen(
                topicUiState = topic,
                onBackClick = onBackClick,
                onFollowClick = { },
                onTopicClick = { },
                onBookmarkChanged = { _, _ -> },
                onNewsResourceViewed = { },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
