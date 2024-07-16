package com.bahaa.task.presentation.mainFragment

sealed class MainIntent {
    object GetTopStore:MainIntent()
    object GetEgyptCoupons:MainIntent()
    object GetUserCoupons:MainIntent()
    object GetBestDeals:MainIntent()
    object GetFeaturedDeals:MainIntent()
    object GetRecentCategories:MainIntent()
    object GetTodayDeals:MainIntent()
    object GetNewYearOffers:MainIntent()
    object GetMotherDayOffers:MainIntent()
}