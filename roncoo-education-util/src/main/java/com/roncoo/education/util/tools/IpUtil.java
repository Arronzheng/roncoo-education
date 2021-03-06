package com.roncoo.education.util.tools;

import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

public class IpUtil {

    public static Optional<Inet4Address> getLocalIp4Address(){
        List<Inet4Address> ipByNi = new ArrayList<>();
        try {
            ipByNi = getLocalIp4AddressFromNetworkInterface();
            if (ipByNi.isEmpty() || ipByNi.size() > 1) {
                final Optional<Inet4Address> ipBySocketOpt = getIpBySocket();
                if (ipBySocketOpt.isPresent()) {
                    return ipBySocketOpt;
                } else {
                    return ipByNi.isEmpty() ? Optional.empty() : Optional.of(ipByNi.get(0));
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return Optional.of(ipByNi.get(0));
    }

    public static String getLocalIp4AddressForString(){
        List<Inet4Address> ipByNi = new ArrayList<>();
        try {
            ipByNi = getLocalIp4AddressFromNetworkInterface();
            if (ipByNi.isEmpty() || ipByNi.size() > 1) {
                final Optional<Inet4Address> ipBySocketOpt = getIpBySocket();
                if (ipBySocketOpt.isPresent()) {
                    return ipBySocketOpt.toString();
                } else {
                    return ipByNi.isEmpty() ? "" : ipByNi.get(0).getHostAddress();
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ipByNi.get(0).getHostAddress();
    }

    private static Optional<Inet4Address> getIpBySocket() throws SocketException {
        try (final DatagramSocket socket = new DatagramSocket()) {
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            if (socket.getLocalAddress() instanceof Inet4Address) {
                return Optional.of((Inet4Address) socket.getLocalAddress());
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public static List<Inet4Address> getLocalIp4AddressFromNetworkInterface() throws SocketException {
        List<Inet4Address> addresses = new ArrayList<>(1);
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        if (e == null) {
            return addresses;
        }
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            if (!isValidInterface(n)) {
                continue;
            }
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                if (isValidAddress(i)) {
                    addresses.add((Inet4Address) i);
                }
            }
        }
        return addresses;
    }

    /**
     * 过滤回环网卡、点对点网卡、非活动网卡、虚拟网卡并要求网卡名字是eth或ens开头
     *
     * @param ni 网卡
     * @return 如果满足要求则true，否则false
     */
    private static boolean isValidInterface(NetworkInterface ni) throws SocketException {
        return !ni.isLoopback() && !ni.isPointToPoint() && ni.isUp() && !ni.isVirtual()
                && (ni.getName().startsWith("eth") || ni.getName().startsWith("ens"));
    }

    /**
     * 判断是否是IPv4，并且内网地址并过滤回环地址.
     */
    private static boolean isValidAddress(InetAddress address) {
        return address instanceof Inet4Address && address.isSiteLocalAddress() && !address.isLoopbackAddress();
    }
}
