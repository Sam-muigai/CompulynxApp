package com.app.compulynx.core.work.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.ServiceInfo
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.app.compulynx.domain.repositories.TransactionRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.io.IOException

@HiltWorker
class SyncLocalTransactionWorker
    @AssistedInject
    constructor(
        @Assisted appContext: Context,
        @Assisted params: WorkerParameters,
        private val transactionRepository: TransactionRepository,
    ) : CoroutineWorker(appContext, params) {
        override suspend fun doWork(): Result {
            setForeground(getForegroundInfo())
            return try {
                transactionRepository.syncLocalTransactions()
                Result.success()
            } catch (e: IOException) {
                e.printStackTrace()
                Result.retry()
            } catch (e: Exception) {
                e.printStackTrace()
                return Result.failure()
            }
        }

        override suspend fun getForegroundInfo(): ForegroundInfo {
            val notificationId = 1
            val channelId = "sync_channel"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel =
                    NotificationChannel(
                        channelId,
                        "Background Sync",
                        NotificationManager.IMPORTANCE_MIN,
                    ).apply {
                        setSound(null, null)
                        enableVibration(false)
                        setShowBadge(false)
                    }

                val manager =
                    applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                manager.createNotificationChannel(channel)
            }

            val notification =
                NotificationCompat.Builder(applicationContext, channelId)
                    .setSmallIcon(android.R.drawable.stat_sys_upload)
                    .setContentTitle("Syncing transactions")
                    .setContentText("Syncing local transactions...")
                    .setPriority(NotificationCompat.PRIORITY_MIN)
                    .setOngoing(true)
                    .setSilent(true)
                    .build()

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ForegroundInfo(
                    notificationId,
                    notification,
                    ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC,
                )
            } else {
                ForegroundInfo(notificationId, notification)
            }
        }

        companion object {
            private val SyncConstraints
                get() =
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()

            fun startUpSyncWork() =
                OneTimeWorkRequestBuilder<SyncLocalTransactionWorker>()
                    .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                    .setConstraints(SyncConstraints)
                    .build()

            const val SYNC_LOCAL_TRANSACTION_WORK_NAME = "SyncLocalTransactionWorker"
        }
    }
