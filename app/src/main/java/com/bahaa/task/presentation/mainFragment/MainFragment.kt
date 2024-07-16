package com.bahaa.task.presentation.mainFragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bahaa.task.R
import com.bahaa.task.databinding.FragmentMainBinding
import com.bahaa.task.domain.models.remoteModels.Category
import com.bahaa.task.domain.models.remoteModels.Coupon
import com.bahaa.task.domain.models.remoteModels.FeaturedDeal
import com.bahaa.task.presentation.mainFragment.adapters.BestDealsAdapter
import com.bahaa.task.presentation.mainFragment.adapters.CategoryAdapter
import com.bahaa.task.presentation.mainFragment.adapters.CouponAdapter
import com.bahaa.task.presentation.mainFragment.adapters.FeaturedDealAdapter
import com.bahaa.task.presentation.mainFragment.adapters.TodayDealsAdapter
import com.bahaa.task.presentation.mainFragment.adapters.TopStoresAdapter
import com.bahaa.task.utils.Response
import dagger.hilt.android.AndroidEntryPoint
import taimoor.sultani.sweetalert2.Sweetalert


@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var loadingDialog: Sweetalert
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        loadingDialog = Sweetalert(requireContext(), Sweetalert.PROGRESS_TYPE)
        loadingDialog.progressHelper.barColor = Color.parseColor("#A5DC86")
        loadingDialog.titleText = getString(R.string.loading)
        loadingDialog.setCancelable(false)
        viewModel.getTopStores()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val topStoresAdapter = TopStoresAdapter()
        binding.topStoresRv.adapter = topStoresAdapter
        lifecycleScope.launchWhenCreated {
            viewModel.topStores.collect {
                when (it) {
                    is Response.Loading -> {
                        loadingDialog.show()
                    }

                    is Response.Success -> {
                        loadingDialog.dismiss()
                        topStoresAdapter.submitList(it.data)
                    }

                    is Response.Error -> {
                        // There is no Exceptions now to handle , we have demo data
                    }
                }
            }
        }

        // to save time Here i don't apply ViewModel and clean architecture
        // above i used them
        val adapter = CouponAdapter()
        adapter.submitList(couponsDemoData)
        binding.bestCouponsEgyptRv.adapter = adapter
        binding.bestCouponsYouRv.adapter = adapter
        binding.newYear.newYearOffersRv.adapter = adapter
        binding.motherDay.motherDayOffersRv.adapter = adapter

        val featuredDealAdapter = FeaturedDealAdapter()
        featuredDealAdapter.submitList(featuredDealsDemoData)
        binding.featuredDealsRv.adapter = featuredDealAdapter

        val bestDealsAdapter = BestDealsAdapter()
        bestDealsAdapter.submitList(couponsDemoData)
        binding.bestDealsRv.adapter = bestDealsAdapter

        val categoryAdapter = CategoryAdapter()
        categoryAdapter.submitList(categoriesDemoData)
        binding.recentCategoriesRv.adapter = categoryAdapter

        val todayDealsAdapter = TodayDealsAdapter()
        todayDealsAdapter.submitList(couponsDemoData)
        binding.todayDealsRv.adapter = todayDealsAdapter
    }

    private val couponsDemoData = listOf(
        Coupon("0","15","https://s3-alpha-sig.figma.com/img/3696/9e01/d09fea31f1d4d4cca4cf7a89a66a01bd?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=g5nJ1HCCvmfMoer5FSGJEti50MfgXBIDvEEcnAC-tqwS58zch8ZG4fhlyzHk47p3b8eh4EmIWN--Hl~sYxWgwz~qoxbvEwiSRwy4qTOauJSvq9Chc4G5SVFnj5YZ2Qe9Pzq88lVAicHkNFbK6WBJItNNn~O00YYsB5Z4YmuvaDuX2Srz2St5-Vsh0gCr3WNgT1qxxsW0Eohd-yCXxJTrPUsXo~2qdndbjNFzhuDznw7nM-87r0OYfrFQd6xxuaKDtIe8wf4d-NwVqBmaWyE9abNA9TGG12lOBkx-Ljj9aLDdkIkLiO98OqIwOAjEjQfXcls5rSIsGXtceEdtJ0OdpQ__"),
        Coupon("1","20","https://s3-alpha-sig.figma.com/img/3696/9e01/d09fea31f1d4d4cca4cf7a89a66a01bd?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=g5nJ1HCCvmfMoer5FSGJEti50MfgXBIDvEEcnAC-tqwS58zch8ZG4fhlyzHk47p3b8eh4EmIWN--Hl~sYxWgwz~qoxbvEwiSRwy4qTOauJSvq9Chc4G5SVFnj5YZ2Qe9Pzq88lVAicHkNFbK6WBJItNNn~O00YYsB5Z4YmuvaDuX2Srz2St5-Vsh0gCr3WNgT1qxxsW0Eohd-yCXxJTrPUsXo~2qdndbjNFzhuDznw7nM-87r0OYfrFQd6xxuaKDtIe8wf4d-NwVqBmaWyE9abNA9TGG12lOBkx-Ljj9aLDdkIkLiO98OqIwOAjEjQfXcls5rSIsGXtceEdtJ0OdpQ__"),
        Coupon("2","30","https://s3-alpha-sig.figma.com/img/3696/9e01/d09fea31f1d4d4cca4cf7a89a66a01bd?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=g5nJ1HCCvmfMoer5FSGJEti50MfgXBIDvEEcnAC-tqwS58zch8ZG4fhlyzHk47p3b8eh4EmIWN--Hl~sYxWgwz~qoxbvEwiSRwy4qTOauJSvq9Chc4G5SVFnj5YZ2Qe9Pzq88lVAicHkNFbK6WBJItNNn~O00YYsB5Z4YmuvaDuX2Srz2St5-Vsh0gCr3WNgT1qxxsW0Eohd-yCXxJTrPUsXo~2qdndbjNFzhuDznw7nM-87r0OYfrFQd6xxuaKDtIe8wf4d-NwVqBmaWyE9abNA9TGG12lOBkx-Ljj9aLDdkIkLiO98OqIwOAjEjQfXcls5rSIsGXtceEdtJ0OdpQ__"),
        Coupon("6","23","https://s3-alpha-sig.figma.com/img/3696/9e01/d09fea31f1d4d4cca4cf7a89a66a01bd?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=g5nJ1HCCvmfMoer5FSGJEti50MfgXBIDvEEcnAC-tqwS58zch8ZG4fhlyzHk47p3b8eh4EmIWN--Hl~sYxWgwz~qoxbvEwiSRwy4qTOauJSvq9Chc4G5SVFnj5YZ2Qe9Pzq88lVAicHkNFbK6WBJItNNn~O00YYsB5Z4YmuvaDuX2Srz2St5-Vsh0gCr3WNgT1qxxsW0Eohd-yCXxJTrPUsXo~2qdndbjNFzhuDznw7nM-87r0OYfrFQd6xxuaKDtIe8wf4d-NwVqBmaWyE9abNA9TGG12lOBkx-Ljj9aLDdkIkLiO98OqIwOAjEjQfXcls5rSIsGXtceEdtJ0OdpQ__")
    )

    private val featuredDealsDemoData = listOf(
        FeaturedDeal("0","60","https://s3-alpha-sig.figma.com/img/78d4/920a/349d376f5544b39b1915acc30f99750b?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=NVF1eeQU2jvnKX5BSeGn61WCVVaPP28KkZDrHLOpKBJdu~RfUv-DJwKNJ7jn8VgTe5hTvXWKof~HIOURt~jDXgey0zl3zScblsiq~0atOXX1I3isn5Xe~lpOMN-pOV0lb8YA8z7fPwxRW0y1XY~TjyZm8YFdvbUWg3qV4OwckNy0jHPaS6agVDrj7PVmnrdCvUQwatUEw0FGsNLFviIPsMAmrYEmDsRMGjlBqIWOy8hv77T309sEUqqDknvZP5RUe258UfD3pNorMhzu1JFOpv22AjldQMQRQsO1pJD5aVG6JleuNmpSgY5-fLlCMnA~GiUrx23f1zFYMJKpSS2Tkw__","Carrefour","50 % from Saudi market\n" +
                "when you used coupon"),
        FeaturedDeal("0","8","https://s3-alpha-sig.figma.com/img/8d05/596f/463158805390b0d0a31a90af5a59f6b3?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Nx-PizGofVZR1vsyxQtNyUXMkcchCLnqWq0A0Vle2Nmq4ZkaNC7NU1bqbq11K1JTi61~kJwOY~vEcRtd1ylG89Z247dbf3QY03O5Wt9hhfkEDeutsfN4w-8p7zlescBPAQKN0-usW7sZSh95J0OMoa-TLG7wqbyV-C1fwWH3AzHPndZ1vxq5Hl1jUbBWbcUSwL8SSx0Q7giM6p~rp2pUpafFET7S7NeC0pnrkWt017Y6eupXWVSjx1QCcDSLTPxjdeptwz8Ah5t5nPEHiyeFjDM7kUMDZz6tIBf7xscdTh7P3EKE8k-V9wrn~LHG512V9YBLUL-GqxtedeHWiW6cdA__","Carrefour","50 % from Saudi market\n" +
                "when you used coupon"),
        FeaturedDeal("0","35","https://s3-alpha-sig.figma.com/img/78d4/920a/349d376f5544b39b1915acc30f99750b?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=NVF1eeQU2jvnKX5BSeGn61WCVVaPP28KkZDrHLOpKBJdu~RfUv-DJwKNJ7jn8VgTe5hTvXWKof~HIOURt~jDXgey0zl3zScblsiq~0atOXX1I3isn5Xe~lpOMN-pOV0lb8YA8z7fPwxRW0y1XY~TjyZm8YFdvbUWg3qV4OwckNy0jHPaS6agVDrj7PVmnrdCvUQwatUEw0FGsNLFviIPsMAmrYEmDsRMGjlBqIWOy8hv77T309sEUqqDknvZP5RUe258UfD3pNorMhzu1JFOpv22AjldQMQRQsO1pJD5aVG6JleuNmpSgY5-fLlCMnA~GiUrx23f1zFYMJKpSS2Tkw__","Carrefour","50 % from Saudi market\n" +
                "when you used coupon"),
        FeaturedDeal("0","72","https://s3-alpha-sig.figma.com/img/78d4/920a/349d376f5544b39b1915acc30f99750b?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=NVF1eeQU2jvnKX5BSeGn61WCVVaPP28KkZDrHLOpKBJdu~RfUv-DJwKNJ7jn8VgTe5hTvXWKof~HIOURt~jDXgey0zl3zScblsiq~0atOXX1I3isn5Xe~lpOMN-pOV0lb8YA8z7fPwxRW0y1XY~TjyZm8YFdvbUWg3qV4OwckNy0jHPaS6agVDrj7PVmnrdCvUQwatUEw0FGsNLFviIPsMAmrYEmDsRMGjlBqIWOy8hv77T309sEUqqDknvZP5RUe258UfD3pNorMhzu1JFOpv22AjldQMQRQsO1pJD5aVG6JleuNmpSgY5-fLlCMnA~GiUrx23f1zFYMJKpSS2Tkw__","Carrefour","50 % from Saudi market\n" +
                "when you used coupon")
      )

    private val categoriesDemoData = listOf(
        Category(
            0,
            "https://s3-alpha-sig.figma.com/img/1db6/bc40/924823a616cd1f31a1614c40c8f2ccfe?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=ZAAAJcKz7MTxluzntLObKlp93aQeER0JnKTyJBrUrlPFpTEhU8EakxDPt1v1HK3pN~4ThAnZjyxtqbxU~hjjm1T0y0AqMVr28GOFtPjsi9~3A1zcDX1AUH7PH~GxHTn6YT5Yc8s9hrfrzN-zPvubHByPXsNxO69FUcNT5M1cZKjUBRfcFowO-t6nQLKqHju92IJHbznlSV0kbAxmsgSGSJ~ZS3MN4xHVQ9XY1Dgh96SyNM7XzkaY70iC~dD-KlTBGlO0VbNgZUOQ0OVgrZMADAMeMsMkMIkUIgFLBAR6JJoUZ-nhcLj3yPSxqbfoO0K64QWuEQWD9VvbURr9cvQzCA__",
            "Fashion"
        ),
        Category(
            1,
            "https://s3-alpha-sig.figma.com/img/a412/2019/ab671bd79ac3552fe665977409bb545b?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=H21RXny9LvSiM26Si6kgn7N2tGVJSYSQ8wsNirrpDql4KpnR9sFpzLLq2Dt-9L6sSiDZG4BxBRGUeTPmvQRhvMXDN3jB~EE~cCJo-2b5IfxrGjseV14x~0vnN0ASYJItH4PiB8YzXTErfeU6rUFtgoK~x3l~QmOB1-f4RjMOVASFtsMJit9ROALkGeA~ESIT4smeCd192ZXyvAFcZSoWDS9ZjUl6ZJdvNgZAmoL75iKMJvoR0y~C4C~MzLHsGC6eI0M1GJtrTJyM3fuhnd7K70R3Mi4WHVCBKCN-mSPQzlNR7kaTprehipl~WI--EVSU09ue6EV5Xo6D~sZ6o1XoRA__",
            "Health"
        ),
        Category(
            5,
            "https://s3-alpha-sig.figma.com/img/3e44/b871/b009011b57d00b9f85d9615e9623daf1?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=EwxbOB7z58m0ZpdlN8L01TDXIVAeKIFBRCoKeU8vizDJwPnnKwrf4iS-b1yuW~x00RiK00bLCs~zMtLVtFratHv9iemVB95KFS~YocBxLtz-6mYUEPpbdRBz7KlHGUbQEAUnXDHONB5vBAhqFuQLAbvLmMCOwvp0gouhsQwKj5Mw5tgyeSxSoyr4fWL0w0nymvzwMSBiXgBiHOgKnh-0L57t-qlOj2RYONGdO-kxv3RjiWYxHLgglTQ2G0AMkDRJF~M~eK23j53Y~zm4CodAzCgdMfOBEk8oweHBzy6PLe4IgxNiinHqRJrEB6Y02H5dR-GmzdkSZxdTDOLGRQLmwQ__",
            "Sports"
        ),
        Category(
            8,
            "https://s3-alpha-sig.figma.com/img/4ef7/09a4/65da0fdb70111d420513bbf437589bf5?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=PGlIW3Qnq5GF~qvmSscaIziBz2WRTjv3MvbFeBzq9fn9kqyDlyaOunCpmdkSK8g1w6nGKWolHh6St-suhz-1n4s~ehpXb7sKnt1jg7GXjoPDqvGy0UkKO5MBjrw1Hp19scV1haTQR27O04NTfW3HveX3QTQlVUMCCHgHPAI4KCkdJ~NUUGU75oiD4GQrHchtnk8ZQcc8cjX7S9zZ1e13FKlfyB5IoZHSMEHATEEFJ5kJOD~nwnjajMCBs1lfZVKosNs64yI41zB0rnpq0idM2HPNbWXJR~6KzBPqyqHt0ia64ebRwLNv02ijiiJoVbgwK8EuBDqVXilXZwIiCJbw3g__",
            "Luxury"
        ),
    )
}