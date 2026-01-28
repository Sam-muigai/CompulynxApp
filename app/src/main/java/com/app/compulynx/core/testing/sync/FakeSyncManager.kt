package com.app.compulynx.core.testing.sync

import com.app.compulynx.core.work.status.SyncManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeSyncManager : SyncManager {
    var syncRequested = false
        private set

    override val isSyncing: Flow<Boolean>
        get() = flowOf(false)

    override fun requestSync() {
        syncRequested = true
    }
}
