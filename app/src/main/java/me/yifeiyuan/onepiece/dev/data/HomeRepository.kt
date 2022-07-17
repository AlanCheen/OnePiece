package me.yifeiyuan.onepiece.dev.data

/**
 * Created by 程序亦非猿 on 2022/7/6.
 */
class HomeRepository(
    private val localDataSource: LocalDataSource = LocalDataSource(),
    private val remoteDataSource: RemoteDataSource = RemoteDataSource()
) {


}