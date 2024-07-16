package com.bahaa.task.data.remote

import com.bahaa.task.domain.MainRepository
import com.bahaa.task.domain.models.remoteModels.TopStore
import com.bahaa.task.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

open class RemoteMainRepositoryImpl @Inject constructor(private val dataApi: DataApi) : MainRepository {

    override suspend fun getTopStores(): Flow<Response<List<TopStore>>> = flow {
        emit(Response.Loading())

        // if app would using locale data base we would insert the data in Room Then use it
        // In this case no Api ... So we would use Demo data
        val topStores = listOf(
            TopStore(
                "1",
                "https://s3-alpha-sig.figma.com/img/da91/9069/02af2c3d0cbc4062bc0372c29a8aa27f?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=caAPU-ZolKj5Pm07ePzvIT1MlDdnImpJwJa3Kuu6TFi7-Dtr~Vmn4nXdm0Robbd6X0q-yvlHmz-mzon7cQ4zLY7EUhEDX9WJxQCzpXzhOxkU~AyV-IO18KmGPHurEc6Hj1GHCPUrvV5cslRjMUWB-uJ0CwFMFsEpOr8OIAacvI~2sFv8jyU2h~N8mu-mbeyamXGts~egVTapB8z66VSldc1rGdVo9CNx8M3a9x6tQvgU6ahq1EdBK83dA-HL-n5sr6hpk1muYvvLdwSVD3z9rupyr1avipo0K7wjcqUHov50Bd5B2GrxcEzQcFyimuflu57vjZaCN6zaxGFHCPPT~g__",
                "Noon"
            ),
            TopStore(
                "2",
                "https://s3-alpha-sig.figma.com/img/fb63/ca00/459c33c112ba8799ea5a59d083110afb?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=nCNTwkYU1VDbucsRTXosPQUwAkSefi3wJTZESx6BXYJBEg48wnLdr4TBpqcWw6sAM9zcagdLuowOooXEirjlYrQL4TGzi61YBPu91shlKit2VpWX5z0qTc-fo0T9SmThLwN~je1qctbkD3fby3WRE9jAqKNaE5oXY0Tv0bl6h0mpODfYA5C4uuFgRht78fcmKfiJpEZX99fru5unvvIbw9JSfyD2gzCW4ClgTjwwJCibSvVCkT41DXZ5aM15PKtSHq8RaMZK4kgzS6gj0D1mf5jO4qxf4yK3les24H2FO999BsB3mFGf7cQXP6-Bd4qKgirLbRYrKS~YQTGEtAaaGQ__",
                "Adidas"
            ),
            TopStore(
                "3",
                "https://s3-alpha-sig.figma.com/img/4650/e13d/94c6ac18ee08987f800b5450fe6ca404?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=pYpmYrQkuLHbmHuSzEa1VOOUd5NrG3e9iJutMjBInSGZc7brLJyng3N7M-w92Wlq~TtBOOs6z2oTMX5cz4rS7tfPZpQLTs~lDO1StViNyHBamzU-R8wenD65hHFXQybx1Sw~jceck0M-B-UDDxwsCjrer4hHf3YF6KM7GeWrYcfq05xxHLb-q3cHgaCin1Gyf3OsQNrAsiaMpQpv49a-bVTH2zCsxqnfrIwJL2vdFkffCkncODYpTZiBaREZUvdVseh5UMf6N6Im~MpMN50lqNCt-p09M-NlEMRwg3jsMVNhSizqvfyosv598Pd86KqFzTXt9sVMfuxVNJ37iSIbeQ__",
                "Panda"
            ),
            TopStore(
                "4",
                "https://s3-alpha-sig.figma.com/img/78d4/920a/349d376f5544b39b1915acc30f99750b?Expires=1722211200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=NVF1eeQU2jvnKX5BSeGn61WCVVaPP28KkZDrHLOpKBJdu~RfUv-DJwKNJ7jn8VgTe5hTvXWKof~HIOURt~jDXgey0zl3zScblsiq~0atOXX1I3isn5Xe~lpOMN-pOV0lb8YA8z7fPwxRW0y1XY~TjyZm8YFdvbUWg3qV4OwckNy0jHPaS6agVDrj7PVmnrdCvUQwatUEw0FGsNLFviIPsMAmrYEmDsRMGjlBqIWOy8hv77T309sEUqqDknvZP5RUe258UfD3pNorMhzu1JFOpv22AjldQMQRQsO1pJD5aVG6JleuNmpSgY5-fLlCMnA~GiUrx23f1zFYMJKpSS2Tkw__",
                "Carrefour"
            )
        )
        emit(Response.Success(topStores))
    }.catch {
        emit(Response.Error(it.message ?: "SomeThing Went Wrong.."))
    }.flowOn(Dispatchers.IO)

}