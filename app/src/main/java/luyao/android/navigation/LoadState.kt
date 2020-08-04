package luyao.android.navigation

sealed class LoadState {
    object Loading: LoadState()
    object Success: LoadState()
    object End: LoadState()
}