// js/app.js — shared utilities for GradeVault

function gradeFromScore(score) {
  if (score >= 90) return { letter: 'A', color: '#22c55e' };
  if (score >= 80) return { letter: 'B', color: '#3b82f6' };
  if (score >= 70) return { letter: 'C', color: '#f59e0b' };
  if (score >= 60) return { letter: 'D', color: '#f97316' };
  return { letter: 'F', color: '#ef4444' };
}

function exportCSV(records) {
  if (!records.length) { alert('No records to export.'); return; }
  const header = 'Name,Subject,Score,Grade,Date,Notes';
  const rows = records.map(r => {
    const g = gradeFromScore(r.score).letter;
    return ['name','subject','score','','date','notes']
      .map((_, i) => {
        const vals = [r.name, r.subject, r.score, g, r.date || '', r.notes || ''];
        return `"${String(vals[i]).replace(/"/g, '""')}"`;
      }).join(',');
  });
  const csv = [header, ...rows].join('\n');
  const blob = new Blob([csv], { type: 'text/csv' });
  const url = URL.createObjectURL(blob);
  const a = document.createElement('a');
  a.href = url; a.download = 'gradevault-export.csv'; a.click();
  URL.revokeObjectURL(url);
}

function highlightActiveNav() {
  const path = window.location.pathname;
  document.querySelectorAll('.nav-item').forEach(el => {
    el.classList.toggle('active', el.getAttribute('href') && path.endsWith(el.getAttribute('href').replace('../','')));
  });
}

document.addEventListener('DOMContentLoaded', highlightActiveNav);
