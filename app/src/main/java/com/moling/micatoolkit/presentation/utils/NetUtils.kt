package com.moling.micatoolkit.presentation.utils

import android.util.Log
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*


fun getIpAddress(): String? {
    try {
        val en: Enumeration<NetworkInterface> = NetworkInterface.getNetworkInterfaces()
        while (en.hasMoreElements()) {
            val nwInterface: NetworkInterface = en.nextElement()
            val enumIpAdder: Enumeration<InetAddress> = nwInterface.inetAddresses
            while (enumIpAdder.hasMoreElements()) {
                val netAddress: InetAddress = enumIpAdder.nextElement()
                if (!netAddress.isLoopbackAddress && !netAddress.isLinkLocalAddress) {
                    return netAddress.hostAddress?.toString()
                }
            }
        }
    } catch (e: SocketException) {
        e.printStackTrace()
    }
    return null
}

fun checkHost(host: String): Boolean {
    val timeout = 5
    var reachableInRetry = 0
    val thread = Thread {
        for (i in 0 until 3) {
            if (InetAddress.getByName(host).isReachable(timeout))
                reachableInRetry++
        }
    }

    thread.start()
    thread.join()

    return reachableInRetry > 0
}

fun InetAddress.getHost(): List<String> {
    val address = this.hostAddress.split(".")
    val ipC = address[0].toLong() shl 24 or
            (address[1].toLong() shl 16 and (0xff shl 16)) or
            (address[2].toLong() shl 8 and (0xff shl 8))

    val localIp = ipC or (address[3].toLong() and 0xff)
    val startIpaddr = ipC or (0 and 0xff)
    val endIpaddr = ipC or (255 and 0xff)

    val hosts = mutableListOf<String>()
    for (i in startIpaddr until endIpaddr) {
        if (i == localIp) {
            continue
        }
        hosts.add(ipToString(i))
    }
    Log.d("MICA", "hosts: $hosts")
    return hosts
}
private fun ipToString(address: Long): String {
    return (address ushr 24 and 0xFF).toString() + "." +
            (address ushr 16 and 0xFF) + "." +
            (address ushr 8 and 0xFF) + "." +
            (address and 0xFF)
}