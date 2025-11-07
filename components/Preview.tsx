"use client";
import { Frame } from "@ark-ui/react/frame";
import { useRef } from "react";

export const Preview = ({ code }: { code: string }) => {
  const ref = useRef<HTMLIFrameElement>(null);

  const srcDoc = `<html>
<head>
<link href="//use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet" />
<link href="//fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
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
      style={{ border: "1px solid #ccc", maxWidth: "800px", width: "100%" }}
      srcDoc={srcDoc}
    ></Frame>
  );
};
