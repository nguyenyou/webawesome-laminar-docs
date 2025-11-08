"use client";
import { Frame } from "@ark-ui/react/frame";
import { DynamicCodeBlock } from 'fumadocs-ui/components/dynamic-codeblock';

export const Preview = ({ code }: { code: string }) => {
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

  const scalaCode = `package examples.webawesome.button.example1
  
import org.scalajs.dom
import com.raquo.laminar.api.L.*
import doc.*
import io.github.nguyenyou.webawesome.laminar.*

@main 
def app = {
  val container = dom.document.querySelector("#root")
  render(container, {
    Examples(
      Button(_.variant.brand)("Brand"),
      Button(_.variant.danger)("Danger"),
      Button(_.variant.neutral)("Neutral"),
      Button(_.variant.success)("Success"),
      Button(_.variant.warning)("Warning"),
    )
  })
}`;
  return (
    <div className="space-y-2">
    <Frame
      title="Custom Frame"
      className="border shadow-sm outline-none w-full rounded-lg"
      // style={{ border: "1px solid #ccc", width: "100%", borderRadius: "8px" }}
      srcDoc={srcDoc}
    ></Frame>
    <DynamicCodeBlock code={scalaCode} lang="scala" />
    </div>
  );
};
