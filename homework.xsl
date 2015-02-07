<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>Company</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
      <th>task</th>
	  <th>platform</th>
	  <th>endDate</th>
	  <th>hint</th>
    </tr>
    <xsl:for-each select="homework">
    <tr>
	  <td><xsl:value-of select="task"/></td>
	  <td><xsl:value-of select="platform"/></td>
	  <td><xsl:value-of select="endDate"/></td>
	  <td><xsl:value-of select="hint"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>