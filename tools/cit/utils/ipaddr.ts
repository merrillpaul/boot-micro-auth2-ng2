import * as os from 'os';

export function getIpAddress(): string {
    let osInterfaces = os.networkInterfaces();
    let ip = "127.0.0.1";
    Object.keys(osInterfaces).forEach(key => {
        let addrs = osInterfaces[key];
        addrs.forEach(addr => {
            if (key.charAt(0) === "e" && "IPv4" === addr.family && !addr.internal) {
				ip = addr.address;
			}
        });
    });

    return ip;
}