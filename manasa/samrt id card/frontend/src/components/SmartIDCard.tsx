import React, { useEffect, useState } from "react";
import QRCode from "qrcode.react";

export default function SmartIDCard() {
  const [profile, setProfile] = useState<any>(null);

  useEffect(() => {
    fetch("http://localhost:3001/api/profile")
      .then((r) => r.json())
      .then(setProfile)
      .catch(() => {});
  }, []);

  const name = profile?.name || "Sreesha Thummalpalli";
  const role = profile?.role || "Student";
  const id = profile?.id || "CARD-524C31C9";

  return (
    <div className="bg-gradient-to-br from-blue-900 to-gray-900 text-white p-6 rounded-xl w-96">
      <h3 className="font-semibold">SMART ID</h3>
      <p className="text-sm">College Card System</p>

      <div className="mt-6 flex justify-between">
        <div>
          <p className="font-bold">{name}</p>
          <p className="text-sm">{role}</p>
        </div>
        <QRCode value={id} size={80} />
      </div>

      <p className="text-xs mt-4">{id}</p>
    </div>
  );
}
