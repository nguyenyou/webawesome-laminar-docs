"use client";
import { Frame } from "@ark-ui/react/frame";
import { useRef } from "react";

export const Preview = ({ code }: { code: string }) => {
  const ref = useRef<HTMLIFrameElement>(null);

  const srcDoc = `<html>
<head>
<link href="//use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet" />
<link href="//cdn.jsdelivr.net/npm/@awesome.me/webawesome@3.0.0/dist-cdn/styles/themes/default.min.css" rel="stylesheet" />
<base target=_blank>
</head>
<body style='overflow: hidden'>
 <div id="root"></div>
 <script type="module">
  ${code}
 </script>
</body>
</html>
`;
  return (
    <Frame
      title="Custom Frame"
      style={{ border: "1px solid #ccc", width: "100%", borderRadius: "8px" }}
      srcDoc={srcDoc}
    ></Frame>
  );
};
