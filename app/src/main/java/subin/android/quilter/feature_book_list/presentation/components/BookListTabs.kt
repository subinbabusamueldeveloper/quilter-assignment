package subin.android.quilter.feature_book_list.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import subin.android.quilter.feature_book_list.domain.model.BookListTab

@Composable
fun BookListTabs(
    selectedTab: BookListTab,
    onTabSelected: (BookListTab) -> Unit
) {
    SecondaryScrollableTabRow(
        selectedTabIndex = BookListTab.entries.indexOf(selectedTab),
        edgePadding = 16.dp,
        divider = {}
    ) {
        BookListTab.entries.forEach { tab ->
            Tab(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                text = {
                    Text(
                        text = tab.title,
                        color = if (selectedTab == tab)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = if (selectedTab == tab)
                            FontWeight.SemiBold
                        else
                            FontWeight.Normal,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            )
        }
    }
}