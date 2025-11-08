"use client";
import { Frame } from "@ark-ui/react/frame";
import { DynamicCodeBlock } from 'fumadocs-ui/components/dynamic-codeblock';

export const Preview = ({ code, userCode }: { code: string; userCode?: string }) => {
  const srcDoc = `<html>
<head>
<link href="//use.fontawesome.com/releases/v5.15.1/css/all.css" rel="stylesheet" />
<link href="//cdn.jsdelivr.net/npm/@awesome.me/webawesome@3.0.0/dist-cdn/styles/themes/default.min.css" rel="stylesheet" />
<base target=_blank>
</head>
<body style='overflow: hidden'>
 <div id="root"></div>
 <script type="module">
  ${code || ""}
 </script>
</body>
</html>
`;

  const displayCode = userCode || "";

  return (
    <div className="space-y-2">
    <Frame
      title="Custom Frame"
      className="border shadow-sm outline-none w-full rounded-lg"
      // style={{ border: "1px solid #ccc", width: "100%", borderRadius: "8px" }}
      srcDoc={srcDoc}
    ></Frame>
    <DynamicCodeBlock code={displayCode} lang="scala" />
    </div>
  );
};
